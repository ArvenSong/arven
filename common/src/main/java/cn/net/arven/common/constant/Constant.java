package cn.net.arven.common.constant;

/**
 * 常量类
 *
 * @author 宋建华
 * @date 2019-04-24 17:02
 **/
public class Constant {
    //文件存放根路径
    public static final String BASE_PATH = "/opt/data/file/";

    //默认显示的图片id
    public static final String NO_PICTURE_ID = "000";

    //默认生成文件名的格式
    public static final String FILE_NAME_DATE_FORMAT = "yyyyMMddHHmmssSSS";

    //文件类型
    public static final String FILE_TYPE_IMAGE = "image";
    public static final String FILE_TYPE_APPLICATION = "application";

    //文件标签
    public static final String FILE_TAG_BANNER = "banner";


    public static final int BANNER_SIZE = 12;


    //图片文件请求地址
    public static final String FILE_SERVICE_URL = "https://arven.net.cn:8764/";
//    public static final String FILE_SERVICE_URL = "https://localhost:8764/";
    public static final String FILE_SERVICE_URL_BREVIARY = FILE_SERVICE_URL + "view/breviary/";
    public static final String FILE_SERVICE_URL_TRUTH = FILE_SERVICE_URL + "view/truth/";
    public static final String FILE_SERVICE_URL_BANNER = FILE_SERVICE_URL + "banner/";
    public static final String FILE_SERVICE_URL_DOWNLOAD = FILE_SERVICE_URL + "download/";


}
