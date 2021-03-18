package cn.net.arven.home.service.impl;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.net.arven.common.constant.Constant;
import cn.net.arven.common.entity.File;
import cn.net.arven.common.util.FileUtil;
import cn.net.arven.home.dao.FileDao;
import cn.net.arven.home.dao.TagDao;
import cn.net.arven.home.service.IFileService;
import cn.net.arven.home.vo.FileVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author
 * @since 2018-12-22
 */
@Service
public class FileServiceImpl extends ServiceImpl<FileDao, File> implements IFileService {

    //nikon
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private TagDao tagDao;

    @Override
    public List<File> getFileByTag(String tag, Integer minSize, Integer crosswise) {
        List<File> fileList = baseMapper.getFileByTag(tag, minSize, crosswise);
        if (CollUtil.isEmpty(fileList)) {
            return Collections.emptyList();
        }
        for (File file : fileList) {
            file.setShowName(file.getShowName().substring(0, file.getShowName().lastIndexOf(".")));
        }
        if (minSize != null) {
            if (fileList.size() < minSize) {
                int num = minSize - fileList.size();
                for (int i = 0; i < num; i++) {
                    fileList.add(fileList.get(i));
                }
            }
        }
        return fileList;
    }


    @Override
    public String saveFile(File fileEntity) {
        if (fileEntity.getCreateTime() == null) {
            fileEntity.setCreateTime(new Date());
        }
        fileEntity.setCreator("001");
        baseMapper.insert(fileEntity);
        return fileEntity.getId();
    }

    @Override
    public String saveMultipartFile(MultipartFile file, String tag) throws IOException {

        if (file.isEmpty()) {
            throw new IOException("file is empty!");
        }

        String showName = file.getOriginalFilename();
        String realName = FileUtil.newDateName(showName);
        java.io.File small = new java.io.File(Constant.STATIC_SMALL_PATH + realName);
        java.io.File large = new java.io.File(Constant.STATIC_LARGE_PATH + realName);
        java.io.File truth = new java.io.File(Constant.STATIC_TRUTH_PATH + realName);
        if (!small.getParentFile().exists()) { //判断文件父目录是否存在
            small.getParentFile().mkdirs();
        }
        if (!large.getParentFile().exists()) { //判断文件父目录是否存在
            large.getParentFile().mkdirs();
        }
        if (!truth.getParentFile().exists()) { //判断文件父目录是否存在
            truth.getParentFile().mkdirs();
        }

        FileUtil.writeBytes(file.getBytes(), truth);
        //水印图片
        BufferedImage watermarkRead = ImageIO.read(new java.io.File("/opt/data/file/watermark.png"));
        BufferedImage bufferedImage = Thumbnails.of(truth).scale(1).asBufferedImage();
        int height = bufferedImage.getHeight();
        int width = bufferedImage.getWidth();
        boolean crosswise = width > height;
        int largeW = 3000;
        int largeH = 2000;
        int smallW = 120;
        int smallH = 90;
        float transparency = 0.5f;
        if (crosswise) {
            Thumbnails.of(truth).size(largeW, largeH).watermark(Positions.BOTTOM_RIGHT, watermarkRead, transparency)
                    .outputQuality(0.5f).toOutputStream(new FileOutputStream(large));
            Thumbnails.of(truth).size(smallW, smallH).toOutputStream(new FileOutputStream(small));
        } else {
            Thumbnails.of(truth).size(largeH, largeW).watermark(Positions.BOTTOM_RIGHT, watermarkRead, transparency)
                    .outputQuality(0.5f).toOutputStream(new FileOutputStream(large));
            Thumbnails.of(truth).size(smallH, smallW).toOutputStream(new FileOutputStream(small));
        }
        File fileEntity = new File();
        fileEntity.setPath(Constant.STATIC_TRUTH_PATH);
        fileEntity.setRealName(realName);
        fileEntity.setShowName(showName);
        fileEntity.setTag(tag);
        fileEntity.setCrosswise(crosswise ? Constant.TRUE : Constant.FALSE);
        fileEntity.setType(cn.net.arven.common.util.FileUtil.getFileType(file));
        if (Constant.FILE_TYPE_IMAGE.equals(fileEntity.getType())) {
            try {
                Metadata metadata = JpegMetadataReader.readMetadata(truth);
                for (Directory directory : metadata.getDirectories()) {
                    for (Tag t : directory.getTags()) {
                        if ("Date/Time".equals(t.getTagName())) {
                            fileEntity.setCreateTime(simpleDateFormat.parse(t.getDescription()));
                            break;
                        }
                    }
                }
            } catch (Exception e) {
            }
        }
        return saveFile(fileEntity);
    }

    @Override
    public Page<FileVO> getAll(Long page,Long limit) {

        Page<FileVO> fileVOPage = baseMapper.selectList(new Page<>(page, limit));
        List<FileVO> records = fileVOPage.getRecords();
        if(CollUtil.isNotEmpty(records)) {
            for (FileVO record : records) {
                if(StrUtil.isNotBlank(record.getTag())) {
                    String[] split = record.getTag().split(",");
                    String tagName = "";
                    for (String tagId : split) {
                        cn.net.arven.common.entity.Tag tag = tagDao.selectById(tagId);
                        if(tag!= null) {
                            tagName += tag.getName() + ",";
                        }
                    }
                    if(StrUtil.isNotBlank(tagName)) {
                        record.setTag(tagName.substring(0, tagName.length() - 1));
                    }
                }
            }
        }
        return fileVOPage;
    }
}
