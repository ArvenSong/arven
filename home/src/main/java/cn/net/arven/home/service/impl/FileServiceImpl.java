package cn.net.arven.home.service.impl;


import cn.hutool.core.collection.CollUtil;
import cn.net.arven.common.constant.Constant;
import cn.net.arven.common.entity.File;
import cn.net.arven.home.dao.FileDao;
import cn.net.arven.home.service.IFileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Collections;
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
    public List<File> getFileByTag(String tag) {
        List<File> fileList = baseMapper.getFileByTag(tag);
        if (CollUtil.isEmpty(fileList)) {
            return Collections.emptyList();
        }
        for (File file : fileList) {
            file.setShowName(file.getShowName().substring(0, file.getShowName().lastIndexOf(".")));
        }
        if (fileList.size() < Constant.BANNER_SIZE) {
            int num = Constant.BANNER_SIZE - fileList.size();
            for (int i = 0; i < num; i++) {
                fileList.add(fileList.get(i));
            }
        }
        return fileList;
    }
}
