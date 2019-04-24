package cn.net.arven.file.controller;

import cn.hutool.core.util.ImageUtil;
import cn.net.arven.common.constant.Constant;
import cn.net.arven.common.entity.File;
import cn.net.arven.file.service.IFileService;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 文件上传
 *
 * @author 宋建华
 * @date 2019-04-23 16:34
 **/
@RestController
public class FileController {


    @Autowired
    IFileService fileService;


    @RequestMapping("/upload")
    public Object upload(MultipartFile file, String tag) throws IOException {

        Map<String, Object> map = new HashMap<>();
        map.put("fileId", fileService.saveMultipartFile(file, tag));
        return map;

    }

    @RequestMapping("/view/truth/{fileId}")
    @ResponseBody
    public ResponseEntity viewTruth(@PathVariable String fileId, HttpServletResponse response) throws IOException {

        File fileEntity = fileService.getById(fileId);

        java.io.File file = new java.io.File(fileEntity.getPath() + fileEntity.getRealName());
        InputStream in = new FileInputStream(file);
        ServletOutputStream out = response.getOutputStream();
        response.setContentType(fileEntity.getType()+"/" + fileEntity.getType());
        int count;
        byte[] buffer = new byte[1024 * 8];
        while ((count = in.read(buffer)) != -1) {
            out.write(buffer, 0, count);
            out.flush();
        }
        if (in != null) {
            in.close();
        }
        if (out != null) {
            out.close();
        }
        return null;

    }

    @RequestMapping("/view/breviary/{fileId}")
    @ResponseBody
    public ResponseEntity breviary(@PathVariable String fileId, HttpServletResponse response) throws IOException {

        File fileEntity = fileService.getById(fileId);
        if (!Constant.FILE_TYPE_IMAGE.equals(fileEntity.getType())) {
            fileEntity = fileService.getById(Constant.NO_PICTURE_ID);
        }
        java.io.File file = new java.io.File(fileEntity.getPath() + fileEntity.getRealName());
        ServletOutputStream out = response.getOutputStream();
        response.setContentType("image/" + fileEntity.getType());

        Thumbnails.of(file).size(160, 160).toOutputStream(out);
        return null;

    }
}
