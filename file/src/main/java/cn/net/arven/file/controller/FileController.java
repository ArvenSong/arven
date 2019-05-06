package cn.net.arven.file.controller;

import cn.net.arven.common.constant.Constant;
import cn.net.arven.common.entity.File;
import cn.net.arven.common.util.FileUtil;
import cn.net.arven.file.service.IFileService;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * 文件上传
 *
 * @author 宋建华
 * @date 2019-04-23 16:34
 **/
@Controller
public class FileController {


    @Autowired
    IFileService fileService;


    /**
     * 文件上传
     *
     * @param file
     * @param tag
     * @return
     * @throws IOException
     */
    @RequestMapping("/upload")
    @ResponseBody
    public Object upload(MultipartFile file, String tag) throws IOException {
        System.gc();
        Map<String, Object> map = new HashMap<>();
        map.put("fileId", fileService.saveMultipartFile(file, tag));
        return map;

    }

    /**
     * 查看原文件
     *
     * @param fileId
     * @return
     */
    @RequestMapping("/view/truth/{fileId}")
    public Object viewTruth(@PathVariable String fileId, ModelAndView modelAndView) {
        System.gc();
        modelAndView.setViewName("view.html");
        File file = fileService.getById(fileId);
        modelAndView.getModel().put("file", file);
        return modelAndView;
    }

    /**
     * 查看缩略图，非图片则显示默认图片
     *
     * @param fileId
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping("/view/breviary/{fileId}")
    public void breviary(@PathVariable String fileId, HttpServletResponse response) throws IOException {
        System.gc();
        File fileEntity = fileService.getById(fileId);
        if (!Constant.FILE_TYPE_IMAGE.equals(fileEntity.getType())) {
            fileEntity = fileService.getById(Constant.NO_PICTURE_ID);
        }
        String fileName = FileUtil.newDateName(fileEntity.getShowName());
        java.io.File file = new java.io.File(fileEntity.getPath() + fileEntity.getRealName());
        ServletOutputStream out = response.getOutputStream();
        response.setContentType(fileEntity.getType() + "/" + FileUtil.getExtensionName(fileName));
        response.setHeader("content-disposition", "attachment;filename="
                + URLEncoder.encode(fileName, "UTF-8"));
        Thumbnails.of(file).size(120, 90).toOutputStream(out);
    }

    /**
     * 下载文件
     *
     * @param fileId
     * @param response
     * @throws IOException
     */
    @RequestMapping("/download/{fileId}")
    public void download(@PathVariable String fileId, HttpServletResponse response) throws IOException {
        System.gc();
        File fileEntity = fileService.getById(fileId);
        String fileName = FileUtil.newDateName(fileEntity.getShowName());
        java.io.File file = new java.io.File(fileEntity.getPath() + fileEntity.getRealName());
        ServletOutputStream out = response.getOutputStream();
        response.setContentType(fileEntity.getType() + "/" + FileUtil.getExtensionName(fileName));
        response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
        FileUtil.writeToStream(file, out);
    }

    /**
     * 下载文件
     *
     * @param fileId
     * @param response
     * @throws IOException
     */
    @RequestMapping("/banner/{fileId}")
    public void banner(@PathVariable String fileId, HttpServletResponse response) throws IOException {
        System.gc();
        File fileEntity = fileService.getById(fileId);
        if (!Constant.FILE_TYPE_IMAGE.equals(fileEntity.getType())) {
            fileEntity = fileService.getById(Constant.NO_PICTURE_ID);
        }
        String fileName = FileUtil.newDateName(fileEntity.getShowName());
        java.io.File file = new java.io.File(fileEntity.getPath() + fileEntity.getRealName());
        ServletOutputStream out = response.getOutputStream();
        response.setContentType(fileEntity.getType() + "/" + FileUtil.getExtensionName(fileName));
        response.setHeader("content-disposition", "attachment;filename="
                + URLEncoder.encode(fileName, "UTF-8"));
        Thumbnails.of(file).size(1680,750).watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new java.io.File("/opt/data/file/watermark.png")), 0.5f)
                .outputQuality(0.5f).toOutputStream(out);
    }
}
