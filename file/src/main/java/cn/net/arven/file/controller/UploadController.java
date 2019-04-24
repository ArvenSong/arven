package cn.net.arven.file.controller;

import cn.hutool.core.collection.CollUtil;
import cn.net.arven.common.entity.File;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * 文件上传
 *
 * @author 宋建华
 * @date 2019-04-23 16:34
 **/
@RequestMapping("/upload")
@RestController
public class UploadController {
    @RequestMapping("/pdf")
    public Object upload(){
        CollUtil.isNotEmpty(new HashMap<>());
        File file = new File();
        file.setId("213213213");
        file.setRealName("rewrewr");
        return file.toString();
    }
}
