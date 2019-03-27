package cn.net.arven.tally.service.impl;

import cn.net.arven.tally.common.util.PageUtils;
import cn.net.arven.tally.dao.NoteDao;
import cn.net.arven.tally.entity.Note;
import cn.net.arven.tally.service.INoteService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author
 * @since 2018-12-13
 */
@Service
public class NoteServiceImpl extends ServiceImpl<NoteDao, Note> implements INoteService {

    @Override
    public List<Note> getAll() {
        return baseMapper.selectAll();
    }

    @Override
    public PageUtils getPage(PageUtils pageUtils) {
        Wrapper<Note> wrapper = new QueryWrapper<>(new Note()).orderByDesc("create_time");

        Page<Note> notePage = new Page<>();
        notePage.setSize(pageUtils.getLimit());
            notePage.setCurrent(pageUtils.getPage());
        IPage<Note> noteIPage = baseMapper.selectPage(notePage, wrapper);
        return new PageUtils(noteIPage);
    }

    @Override
    public int saveNote(Note note) {
        Note lastNote = baseMapper.selectLastOne();
        note.setRemain(lastNote.getRemain() - note.getAmount());
        note.setCreator("001");
        note.setCreateTime(new Date());
        return baseMapper.insert(note);
    }
}
