package cn.net.arven.tally.service;

import cn.net.arven.tally.common.util.PageUtils;
import cn.net.arven.tally.entity.Note;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2018-12-13
 */
public interface INoteService extends IService<Note> {

    List<Note> getAll();

    PageUtils getPage(PageUtils pageMap);

    int saveNote(Note note);
}
