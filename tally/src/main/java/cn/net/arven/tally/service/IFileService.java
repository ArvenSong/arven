package cn.net.arven.tally.service;

import cn.net.arven.tally.entity.File;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2018-12-22
 */
public interface IFileService extends IService<File> {

    String saveFile(File fileEntity);
}
