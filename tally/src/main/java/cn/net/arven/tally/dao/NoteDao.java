package cn.net.arven.tally.dao;

import cn.net.arven.tally.entity.Note;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 
 * @since 2018-12-13
 */
public interface NoteDao extends BaseMapper<Note> {
    List<Note> selectAll();


    Note selectLastOne();

}
