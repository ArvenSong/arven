package cn.net.arven.home.dao;

import cn.net.arven.common.entity.File;
import cn.net.arven.home.vo.FileVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 
 * @since 2018-12-22
 */
@Repository
public interface FileDao extends BaseMapper<File> {

    List<File> getFileByTag(@Param("tag")String tag, @Param("limit")Integer limit,@Param("crosswise")Integer crosswise);

    Page<FileVO> selectList(Page<FileVO> fileVOPage);
}
