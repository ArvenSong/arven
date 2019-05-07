package cn.net.arven.home.dao;

import cn.net.arven.common.entity.File;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

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

    List<File> getFileByTag(@Param("tag")String tag, @Param("limit")Integer limit);

}
