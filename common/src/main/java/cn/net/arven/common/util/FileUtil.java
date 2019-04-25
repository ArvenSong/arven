package cn.net.arven.common.util;

import cn.hutool.core.date.DateUtil;
import cn.net.arven.common.constant.Constant;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * 文件工具类
 *
 * @author 宋建华
 * @date 2019-04-24 16:01
 **/
public class FileUtil extends cn.hutool.core.io.FileUtil {

    private FileUtil() {
    }

    /**
     * 获取文件类型
     *
     * @param file
     * @return
     */
    public static String getFileType(MultipartFile file) {
        String fileType = "";
        try {
            fileType = file.getContentType().split("/")[0].toLowerCase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileType;
    }

    /**
     * 根据当前时间生成新的文件名
     *
     * @param showName
     * @param format
     * @return
     */
    public static String newDateName(String showName, String format) {

        String extensionName = showName.substring(showName.lastIndexOf('.'), showName.length());
        return DateUtil.format(new Date(), format) + extensionName;
    }

    public static String newDateName(String showName) {
        return newDateName(showName, Constant.FILE_NAME_DATE_FORMAT);
    }

    /**
     * 获取文件扩展名，不带‘.’
     *
     * @param showName
     * @return
     */
    public static String getExtensionName(String showName) {
        return showName.substring(showName.lastIndexOf('.') + 1, showName.length());
    }
}
