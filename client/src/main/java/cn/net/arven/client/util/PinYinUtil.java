package cn.net.arven.client.util;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 宋建华
 * @date 2019-04-26 15:11
 **/
public class PinYinUtil {
    private static Map<String, String> dictionaries = new HashMap<>();

    public static void main(String[] args) {
        String dicVal = "hang2lie4shi4";

        int beginIndex = 0;

        List<String> list = new ArrayList<>();
        for (int i = 0; i < dicVal.length(); i++) {
            try {
                Integer.parseInt("" + dicVal.charAt(i));
                list.add(dicVal.substring(beginIndex, i + 1));
                if (i < dicVal.length() - 1) {
                    beginIndex = i + 1;
                }

            } catch (NumberFormatException e) {
            }
        }
        System.err.println(toPinyin("兔子"));

    }


    /**
     * 48      * 汉字转为拼音
     * 49      * @param chinese
     * 50      * @return
     * 51
     */
    public static String toPinyin(String chinese) {
        String pinyinStr = "";
        char[] newChar = chinese.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setVCharType(HanyuPinyinVCharType.WITH_U_UNICODE);
        for (int i = 0; i < newChar.length; i++) {
            if (newChar[i] > 128) {
                try {
                    int yinDiaoNo = getYinDiaoNo(chinese, i, defaultFormat);
                    defaultFormat.setToneType(HanyuPinyinToneType.WITH_TONE_MARK);
                    pinyinStr += PinyinHelper.toHanyuPinyinStringArray(newChar[i], defaultFormat)[yinDiaoNo] + ",";
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            } else {
                pinyinStr += newChar[i];
            }
        }
        return pinyinStr;
    }

    /**
     * 获取单个汉字的使用的第几个拼音
     *
     * @param chinese
     * @param index
     * @return
     */
    private static int getYinDiaoNo(String chinese, int index, HanyuPinyinOutputFormat defaultFormat) throws BadHanyuPinyinOutputFormatCombination {
        defaultFormat.setToneType(HanyuPinyinToneType.WITH_TONE_NUMBER);
        String[] pinYins = PinyinHelper.toHanyuPinyinStringArray(chinese.charAt(index), defaultFormat);
        List<String> yinDiaoList = getYinDiaoList(chinese);
        if (yinDiaoList == null) {
            return 0;
        }
        for (int i = 0; i < pinYins.length; i++) {
            if (yinDiaoList.contains(pinYins[i])) {
                return i;
            }
        }
        return 0;
    }

    /**
     * 根据字典定义获取音调
     *
     * @param chinese
     * @return
     */
    private static List<String> getYinDiaoList(String chinese) {
        String dicVal = dictionaries.get(chinese);
        if (StrUtil.isBlank(dicVal)) {
            return null;
        }
        int beginIndex = 0;
        List<String> list = new ArrayList<>();
        for (int i = 0; i < dicVal.length(); i++) {
            try {
                Integer.parseInt("" + dicVal.charAt(i));
                list.add(dicVal.substring(beginIndex, i + 1));
                if (i < dicVal.length() - 1) {
                    beginIndex = i + 1;
                }
            } catch (NumberFormatException e) {
            }
        }
        return list;
    }


    /**
     * 数组里面每个长度为6，不足补空格
     *
     * @param pinYins
     * @return
     */
    public static String[] config(String[] pinYins) {
        String[] strs = new String[10];
        for (int i = 0; i < pinYins.length; i++) {
            String pinYin = pinYins[i];
            String newPinYin = pinYin;
            //zhuang
            if (pinYin.length() < 5) {
                int end = 3 - pinYin.length() / 2;//后面补的空格数
                int begin = 6 - end - pinYin.length();//前面补的空格数
                newPinYin = space(space(pinYin, begin, true), end, false);
            }
            strs[i] = newPinYin;
        }
        return strs;
    }


    /**
     * 补空格
     *
     * @param s
     * @param spaceNumber
     * @param isBefore
     * @return
     */
    public static String space(String s, int spaceNumber, boolean isBefore) {
        for (int i = 0; i < spaceNumber; i++) {
            if (isBefore) {
                s = " " + s;
            } else {
                s = s + " ";
            }
        }
        return s;
    }


    public static String toPinyin(char chinese) {
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITH_TONE_MARK);
        defaultFormat.setVCharType(HanyuPinyinVCharType.WITH_U_UNICODE);
        try {
            return PinyinHelper.toHanyuPinyinStringArray(chinese, defaultFormat)[0];
        } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
            badHanyuPinyinOutputFormatCombination.printStackTrace();
        }
        return null;
    }

    public static void init(String filePath) {
        List<String> list = FileUtil.readLines(new File(filePath), "GBK");
        for (String line : list) {
            String[] kv = line.split("=");
            dictionaries.put(kv[0].trim(), kv[1].trim());
        }
    }
}
