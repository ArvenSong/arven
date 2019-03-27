package cn.net.arven.tally.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.net.arven.tally.service.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class FileUpload {
    private String filePath = "/opt/tally/file/note/ticket/";

    @Autowired
    IFileService fileService;

    @RequestMapping("/file/note/ticket")
    @ResponseBody
    public Object fileUpload(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return "false";
        }
        String showName = file.getOriginalFilename();
        String extensionName = showName.substring(showName.lastIndexOf('.'), showName.length());
        String realName = DateUtil.format(new Date(), "yyyyMMddHHmmssSSS") + extensionName;
        File dest = new File(filePath + realName);
        if (!dest.getParentFile().exists()) { //判断文件父目录是否存在
            dest.getParentFile().mkdirs();
        }

        FileUtil.writeBytes(file.getBytes(), dest);
        cn.net.arven.tally.entity.File fileEntity = new cn.net.arven.tally.entity.File();
        fileEntity.setPath(filePath);
        fileEntity.setRealName(realName);
        fileEntity.setShowName(showName);
        fileEntity.setType(extensionName.substring(1, extensionName.length()));
        Map<String,Object> map = new HashMap<>();
        map.put("fileId", fileService.saveFile(fileEntity));
        return map;

    }

    @RequestMapping("/view/note/ticket/{fileId}")
    @ResponseBody
    public ResponseEntity viewPicture(@PathVariable String fileId, HttpServletResponse response) throws IOException {
        cn.net.arven.tally.entity.File fileEntity = fileService.getById(fileId);
        File file = new File(fileEntity.getPath() + fileEntity.getRealName());
        InputStream in = new FileInputStream(file);
        ServletOutputStream out = response.getOutputStream();
        response.setContentType("image/" + fileEntity.getType());
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


}
