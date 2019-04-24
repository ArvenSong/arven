package cn.net.arven.common.util;

import org.springframework.web.multipart.MultipartFile;

/**
 * 文件工具类
 *
 * @author 宋建华
 * @date 2019-04-24 16:01
 **/
public class FileUtil {

    private FileUtil(){}

    public static String getFileType(MultipartFile file) {
        String fileType = "";
        try {
            fileType = file.getContentType().split("/")[0].toLowerCase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileType;
    }
}
