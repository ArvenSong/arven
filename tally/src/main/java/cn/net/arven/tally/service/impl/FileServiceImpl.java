package cn.net.arven.tally.service.impl;

import cn.net.arven.tally.dao.FileDao;
import cn.net.arven.tally.entity.File;
import cn.net.arven.tally.service.IFileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 *  服务实现类
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
}
