package cn.net.arven.client.util;

import ij.IJ;
import ij.ImagePlus;
import ij.ImageStack;
import ij.gui.Overlay;
import ij.gui.Roi;
import ij.io.Opener;
import ij.process.ImageProcessor;

import java.awt.*;
import java.util.UUID;

/**
 * 图片工具类
 *
 * @author 宋建华
 * @date 2019-03-28 09:40
 **/
public class ImageUtil {

    public static void main(String[] args) {
        String desktop = "C:\\Users\\58273\\Desktop\\";
        String a4blank = "picture\\A4blank.jpg";
        String source = "picture\\source";
        String sourceOne = "picture\\source\\狼.jpg";
//        String sourceOne = "picture\\source\\鸽子.jpg";
        String finished = "picture\\finished\\";
        Opener opener = new Opener();
        ImagePlus blankImp = opener.openImage(desktop + a4blank);
        ImagePlus oneImp = opener.openImage(desktop + sourceOne);


        joinPicture(blankImp, oneImp, 0);
        joinPicture(blankImp, oneImp, 1);
        joinPicture(blankImp, oneImp, 2);
        joinPicture(blankImp, oneImp, 3);



//        blankImp.show();

      IJ.saveAs(blankImp, "jpeg", desktop+finished+UUID.randomUUID());
    }

    /**
     * 合成图片
     *
     * @param blankImp
     * @param oneImp
     * @param type
     */
    private static void joinPicture(ImagePlus blankImp, ImagePlus oneImp, int type) {
        ImageProcessor blankIp = blankImp.getProcessor();
        ImageProcessor oneIp = oneImp.getProcessor();
        int width = oneIp.getWidth();
        int height = oneIp.getHeight();
        System.err.println("width:" + width + "\theight:" + height);
        double pixel = 118.11;//像素/厘米
        double normWidth = 9.3;//标准图片缩放宽度cm
        double normHeight = 8.5;//标准图片缩放高度cm
        int newWidth = (int) (normWidth * pixel);
        int newHeight = (int) (normHeight * pixel);


        double[] loc = getLoc(type);
        int xLoc = (int) (loc[0] * pixel);//
        int yLoc = (int) (loc[1] * pixel);
        oneIp.setInterpolate(true);
        ImageProcessor resize;
        if (height > width) {
            newWidth = (int) ((double) newHeight / height * width);
            resize = oneIp.resize(newWidth);
            xLoc = (int) ((normWidth * pixel) / 2 + xLoc - (double) resize.getWidth() / 2);
        } else {
            resize = oneIp.resize(newWidth);
            yLoc = (int) ((normHeight * pixel) / 2 + yLoc - (double) resize.getHeight() / 2);
        }

        System.err.println("newWidth:" + resize.getWidth() + "\tnewHeight:" + resize.getHeight());
        System.err.println("xLoc:" + xLoc + "\tyLoc:" + yLoc);
        blankIp.setInterpolate(true); // bilinear
        blankIp.insert(resize, xLoc, yLoc);


        Font font = new Font("微软雅黑", Font.PLAIN, 300);// 添加字体的属性设置
        blankIp.setFont(font);

        int xWord = (int) (loc[2] * pixel);
        int yWord = (int) (loc[3] * pixel);


        blankIp.drawString("狼", xWord, yWord);


    }

    /**
     * 获取相对边距位置（cm）
     *
     * @param type
     * @return
     */
    private static double[] getLoc(int type) {
        double[] loc = new double[4];
        switch (type) {
            case 0:
                loc[0] = 1.2;
                loc[1] = 1.1;
                loc[2] = 11;
                loc[3] = 6.8;
                break;
            case 1:
                loc[0] = 16;
                loc[1] = 1.1;
                loc[2] = 26;
                loc[3] = 6.8;
                break;
            case 2:
                loc[0] = 1.2;
                loc[1] = 11.6;
                loc[2] = 11;
                loc[3] = 17;
                break;
            case 3:
                loc[0] = 16;
                loc[1] = 11.6;
                loc[2] = 26;
                loc[3] = 17;
                break;
            default:
        }
        return loc;
    }

}
