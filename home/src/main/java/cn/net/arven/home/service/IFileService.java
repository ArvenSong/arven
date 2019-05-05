package cn.net.arven.home.service;

import cn.net.arven.common.entity.File;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author
 * @since 2018-12-22
 */
public interface IFileService extends IService<File> {

    List<File> getFileByTag(String tag);
}
