package cn.net.arven.home.dao;

import cn.net.arven.common.entity.File;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 
 * @since 2018-12-22
 */
public interface FileDao extends BaseMapper<File> {

    List<File> getFileByTag(String tag);

}
