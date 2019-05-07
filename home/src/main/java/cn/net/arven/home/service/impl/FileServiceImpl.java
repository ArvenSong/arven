package cn.net.arven.home.service.impl;


import cn.hutool.core.collection.CollUtil;
import cn.net.arven.common.constant.Constant;
import cn.net.arven.common.entity.File;
import cn.net.arven.common.util.FileUtil;
import cn.net.arven.home.dao.FileDao;
import cn.net.arven.home.service.IFileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

    @Override
    public List<File> getFileByTag(String tag, Integer minSize) {
        List<File> fileList = baseMapper.getFileByTag(tag);
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
        Thumbnails.of(truth).size(6000, 4000).toOutputStream(new FileOutputStream(large));
        Thumbnails.of(truth).size(120, 90).toOutputStream(new FileOutputStream(small));

        File fileEntity = new File();
        fileEntity.setPath(Constant.STATIC_TRUTH_PATH);
        fileEntity.setRealName(realName);
        fileEntity.setShowName(showName);
        fileEntity.setTag(tag);
        fileEntity.setType(cn.net.arven.common.util.FileUtil.getFileType(file));
        if (Constant.FILE_TYPE_IMAGE.equals(fileEntity.getType())) {
            try {
                Metadata metadata = JpegMetadataReader.readMetadata(new java.io.File(fileEntity.getPath() + fileEntity.getRealName()));
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
}
