package cn.net.arven.client.util;

import ij.IJ;
import ij.ImagePlus;
import ij.io.Opener;
import ij.process.ImageProcessor;

import java.awt.*;
import java.io.File;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfWriter;

/**
 * 图片工具类
 *
 * @author 宋建华
 * @date 2019-03-28 09:40
 **/
public class ImageUtil {

    public static void main(String[] args) {
        String desktop = "C:\\";
        String a4blank = "picture\\A4blank.jpg";
        String a4blankLabel = "picture\\labelBlank.jpg";
        String source = "picture\\source";

        String finished = "picture\\finished\\";
        produce(desktop, a4blank, a4blankLabel, source, finished);
    }

    /**
     * 运行
     * @param rootFolder
     * @param a4blank
     * @param a4blankLabel
     * @param source
     * @param finished
     */
    private static void produce(String rootFolder, String a4blank, String a4blankLabel, String source, String finished) {
        Opener opener = new Opener();
        ImagePlus blankImp = opener.openImage(rootFolder + a4blank);
        ImagePlus blankLabelImp = opener.openImage(rootFolder + a4blankLabel);

        File folder = new File(rootFolder + source);
        List<File> tempList = Arrays.asList(folder.listFiles());

        for (File file : tempList) {
            String name = file.getName().split("\\.")[0];
            ImagePlus oneImp = opener.openImage(file.getAbsolutePath());
            joinPicture(blankImp, oneImp, tempList.indexOf(file)%4, name);
            joinLabel(blankLabelImp, tempList.indexOf(file) % 12, name);
            if (tempList.indexOf(file)%4==3||tempList.indexOf(file)==tempList.size()-1){
                IJ.saveAs(blankImp, "jpeg", rootFolder +finished+UUID.randomUUID());
                blankImp.revert();
            }
            if (tempList.indexOf(file) % 12 == 11 || tempList.indexOf(file) == tempList.size() - 1) {
                IJ.saveAs(blankLabelImp, "jpeg", rootFolder + finished + UUID.randomUUID());
//                blankLabelImp.show();
                blankLabelImp.revert();
            }

        }
        toPdf(rootFolder + finished, rootFolder + finished+new Date().getTime()+".pdf");
    }

    /**
     * 合成标签
     *
     * @param blankLabelImp
     * @param index
     * @param name
     */
    private static void joinLabel(ImagePlus blankLabelImp, int index, String name) {
        ImageProcessor blankIp = blankLabelImp.getProcessor();
        int xWord;
        int yWord;
        double pixel = 118.11;//像素/厘米
        Font font = new Font("微软雅黑", Font.PLAIN, 300);// 添加字体的属性设置
        blankIp.setFont(font);
        double[] loc = getLabelLoc(index, name.length());

        switch (name.length()) {
            case 3:
                xWord = (int) (loc[6] * pixel);
                yWord = (int) (loc[7] * pixel);
                blankIp.drawString(String.valueOf(name.charAt(2)), xWord, yWord);
            case 2:
                xWord = (int) (loc[4] * pixel);
                yWord = (int) (loc[5] * pixel);
                blankIp.drawString(String.valueOf(name.charAt(1)), xWord, yWord);
            case 1:
                xWord = (int) (loc[2] * pixel);
                yWord = (int) (loc[3] * pixel);
                blankIp.drawString(String.valueOf(name.charAt(0)), xWord, yWord);
            default:
        }

    }

    private static double[] getLabelLoc(int index, int nameLength) {
        double[] loc = new double[10];
        switch (index) {
            case 0:
                loc[0] = 1.2;
                loc[1] = 1.1;
                loc[2] = 1.3;
                getLocTop(nameLength, loc);
                break;
            case 1:
                loc[0] = 1.2;
                loc[1] = 1.1;
                loc[2] = 6.25;
                getLocTop(nameLength, loc);
                break;
            case 2:
                loc[0] = 1.2;
                loc[1] = 1.1;
                loc[2] = 11;
                getLocTop(nameLength, loc);
                break;


            case 3:
                loc[0] = 16;
                loc[1] = 1.1;
                loc[2] = 16.1;
                getLocTop(nameLength, loc);

                break;
            case 4:
                loc[0] = 16;
                loc[1] = 1.1;
                loc[2] = 21.1;
                getLocTop(nameLength, loc);

                break;
            case 5:
                loc[0] = 16;
                loc[1] = 1.1;
                loc[2] = 25.9;
                getLocTop(nameLength, loc);

                break;
            case 6:
                loc[0] = 1.2;
                loc[1] = 11.6;
                loc[2] = 1.3;
                getLocBottom(nameLength, loc);
                break;
            case 7:
                loc[0] = 1.2;
                loc[1] = 11.6;
                loc[2] = 6.25;
                getLocBottom(nameLength, loc);
                break;
            case 8:
                loc[0] = 1.2;
                loc[1] = 11.6;
                loc[2] = 11;
                getLocBottom(nameLength, loc);
                break;
            case 9:
                loc[0] = 16;
                loc[1] = 11.6;
                loc[2] = 16.1;
                getLocBottom(nameLength, loc);
                break;
            case 10:
                loc[0] = 16;
                loc[1] = 11.6;
                loc[2] = 21.1;
                getLocBottom(nameLength, loc);
                break;
            case 11:
                loc[0] = 16;
                loc[1] = 11.6;
                loc[2] = 25.9;
                getLocBottom(nameLength, loc);
                break;
            default:
        }
        return loc;
    }


    /**
     * 合成图片
     *
     * @param blankImp
     * @param oneImp
     * @param type
     */
    private static void joinPicture(ImagePlus blankImp, ImagePlus oneImp, int type, String name) {
        ImageProcessor blankIp = blankImp.getProcessor();
        ImageProcessor oneIp = oneImp.getProcessor();
        int width = oneIp.getWidth();
        int height = oneIp.getHeight();
//        System.err.println("width:" + width + "\theight:" + height);
        double pixel = 118.11;//像素/厘米
        double normWidth = 9.3;//标准图片缩放宽度cm
        double normHeight = 8.5;//标准图片缩放高度cm
        int newWidth = (int) (normWidth * pixel);
        int newHeight = (int) (normHeight * pixel);


        double[] loc = getLoc(type, name.length());
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

//        System.err.println("newWidth:" + resize.getWidth() + "\tnewHeight:" + resize.getHeight());
//        System.err.println("xLoc:" + xLoc + "\tyLoc:" + yLoc);
        blankIp.setInterpolate(true); // bilinear
        blankIp.insert(resize, xLoc, yLoc);


        drawWord(name, blankIp, pixel, loc);


    }

    /**
     * 插入文字
     *
     * @param name
     * @param blankIp
     * @param pixel
     * @param loc
     */
    private static void drawWord(String name, ImageProcessor blankIp, double pixel, double[] loc) {
        int xWord;
        int yWord;
        Font font = new Font("微软雅黑", Font.PLAIN, 300);// 添加字体的属性设置
        blankIp.setFont(font);

        switch (name.length()) {
            case 3:
                xWord = (int) (loc[6] * pixel);
                yWord = (int) (loc[7] * pixel);
                blankIp.drawString(String.valueOf(name.charAt(2)), xWord, yWord);
            case 2:
                xWord = (int) (loc[4] * pixel);
                yWord = (int) (loc[5] * pixel);
                blankIp.drawString(String.valueOf(name.charAt(1)), xWord, yWord);
            case 1:
                xWord = (int) (loc[2] * pixel);
                yWord = (int) (loc[3] * pixel);
                blankIp.drawString(String.valueOf(name.charAt(0)), xWord, yWord);
            default:
        }
    }

    /**
     * 获取相对边距位置（cm）
     *
     * @param type
     * @return
     */
    private static double[] getLoc(int type, int nameLength) {
        double[] loc = new double[10];
        switch (type) {
            case 0:
                loc[0] = 1.2;
                loc[1] = 1.1;
                loc[2] = 11;
                getLocTop(nameLength, loc);
                break;
            case 1:
                loc[0] = 16;
                loc[1] = 1.1;
                loc[2] = 25.9;
                getLocTop(nameLength, loc);

                break;
            case 2:
                loc[0] = 1.2;
                loc[1] = 11.6;
                loc[2] = 11;
                getLocBottom(nameLength, loc);
                break;
            case 3:
                loc[0] = 16;
                loc[1] = 11.6;
                loc[2] = 25.9;
                getLocBottom(nameLength, loc);
                break;
            default:
        }
        return loc;
    }

    /**
     * 第一横排y值相同
     *
     * @param nameLength
     * @param loc
     */
    private static void getLocTop(int nameLength, double[] loc) {
        if (nameLength == 1) {
            loc[3] = 6.8;
        } else if (nameLength == 2) {
            loc[3] = 4.8;
            loc[4] = loc[2];
            loc[5] = 8.8;

        } else if (nameLength == 3) {
            loc[3] = 4.2;
            loc[4] = loc[2];
            loc[5] = 6.9;
            loc[6] = loc[2];
            loc[7] = 9.5;

        }
    }

    /**
     * 第二横排y值相同
     *
     * @param nameLength
     * @param loc
     */
    private static void getLocBottom(int nameLength, double[] loc) {
        if (nameLength == 1) {
            loc[3] = 17;
        } else if (nameLength == 2) {
            loc[3] = 15.3;
            loc[4] = loc[2];
            loc[5] = 19.3;

        } else if (nameLength == 3) {
            loc[3] = 14.7;
            loc[4] = loc[2];
            loc[5] = 17.4;
            loc[6] = loc[2];
            loc[7] = 20;
        }
    }


    /**
     *
     * @param imageFolderPath
     *            图片文件夹地址
     * @param pdfPath
     *            PDF文件保存地址
     *
     */
    public static void toPdf(String imageFolderPath, String pdfPath) {
        try {
            // 图片文件夹地址
            // String imageFolderPath = "D:/Demo/ceshi/";
            // 图片地址
            String imagePath = null;
            // PDF文件保存地址
            // String pdfPath = "D:/Demo/ceshi/hebing.pdf";
            // 输入流
            FileOutputStream fos = new FileOutputStream(pdfPath);
            // 创建文档
            Document doc = new Document(null, 0, 0, 0, 0);
            //doc.open();
            // 写入PDF文档
            PdfWriter.getInstance(doc, fos);
            // 读取图片流
            BufferedImage img = null;
            // 实例化图片
            Image image = null;
            // 获取图片文件夹对象
            File file = new File(imageFolderPath);
            File[] files = file.listFiles();
            // 循环获取图片文件夹内的图片
            for (File file1 : files) {
                if (file1.getName().endsWith(".png")
                        || file1.getName().endsWith(".jpg")
                        || file1.getName().endsWith(".gif")
                        || file1.getName().endsWith(".jpeg")
                        || file1.getName().endsWith(".tif")) {
                    // System.out.println(file1.getName());
                    imagePath = imageFolderPath + file1.getName();
//                    System.out.println(file1.getName());
                    // 读取图片流
                    img = ImageIO.read(new File(imagePath));
                    // 根据图片大小设置文档大小
                    doc.setPageSize(new Rectangle(img.getWidth(), img
                            .getHeight()));
                    // 实例化图片
                    image = Image.getInstance(imagePath);
                    // 添加图片到文档
                    doc.open();
                    doc.add(image);
                }
            }
            // 关闭文档
            doc.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BadElementException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }


}
