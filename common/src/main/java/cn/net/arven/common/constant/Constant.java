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
    public static final String FILE_TAG_PHOTOGRAPHER = "photographer";
    public static final String FILE_TAG_GALLERY = "gallery";
    public static final String FILE_TAG_EXPO2019 = "EXPO2019";


    public static final int BANNER_SIZE = 12;


    //图片文件请求地址
    public static final String FILE_SERVICE_URL = "/";
//    public static final String FILE_SERVICE_URL = "https://localhost:8764/";
    public static final String FILE_SERVICE_URL_BREVIARY = FILE_SERVICE_URL + "view/breviary/";
    public static final String FILE_SERVICE_URL_TRUTH = FILE_SERVICE_URL + "view/truth/";
    public static final String FILE_SERVICE_URL_BANNER = FILE_SERVICE_URL + "banner/";
    public static final String FILE_SERVICE_URL_DOWNLOAD = FILE_SERVICE_URL + "download/";

    public static final String STATIC_SMALL_URL = "/small/";
    public static final String STATIC_LARGE_URL = "/large/";
    public static final String STATIC_TRUTH_URL = "/truth/";

    //图片存放路径
    public static final String STATIC_SMALL_PATH = "/opt/data/small/";
    public static final String STATIC_LARGE_PATH = "/opt/data/large/";
    public static final String STATIC_TRUTH_PATH = "/opt/data/truth/";

    //是和否
    public static final Integer TRUE = 1;
    public static final Integer FALSE = 0;


}
