package cn.net.arven.file.service.impl;


import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.net.arven.common.constant.Constant;
import cn.net.arven.common.entity.File;
import cn.net.arven.file.dao.FileDao;
import cn.net.arven.file.service.IFileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    @Override
    public String saveFile(File fileEntity) {
        fileEntity.setCreateTime(new Date());
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
        String extensionName = showName.substring(showName.lastIndexOf('.'), showName.length());
        String realName = DateUtil.format(new Date(), "yyyyMMddHHmmssSSS") + extensionName;
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
        fileEntity.setType(cn.net.arven.common.util.FileUtil.getFileType(file));
        return saveFile(fileEntity);
    }
}
