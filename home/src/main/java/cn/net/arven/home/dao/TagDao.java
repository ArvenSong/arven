package cn.net.arven.home.dao;

import cn.net.arven.common.entity.Tag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 
 * @since 2021-03-14
 */
public interface TagDao extends BaseMapper<Tag> {


    List<Tag> selectList();
}
