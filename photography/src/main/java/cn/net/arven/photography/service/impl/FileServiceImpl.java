package cn.net.arven.photography.service.impl;


import cn.net.arven.common.constant.Constant;
import cn.net.arven.common.entity.File;
import cn.net.arven.common.util.FileUtil;
import cn.net.arven.photography.dao.FileDao;
import cn.net.arven.photography.service.IFileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        java.io.File dest = new java.io.File(Constant.BASE_PATH + realName);
        if (!dest.getParentFile().exists()) { //判断文件父目录是否存在
            dest.getParentFile().mkdirs();
        }
        FileUtil.writeBytes(file.getBytes(), dest);
        File fileEntity = new File();
        fileEntity.setPath(Constant.BASE_PATH);
        fileEntity.setRealName(realName);
        fileEntity.setShowName(showName);
        fileEntity.setTag(tag);
        fileEntity.setType(FileUtil.getFileType(file));
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
