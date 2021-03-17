package cn.net.arven.home.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.net.arven.common.entity.Tag;
import cn.net.arven.home.dao.TagDao;
import cn.net.arven.home.service.ITagService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 
 * @since 2021-03-14
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagDao, Tag> implements ITagService {


    @Override
    public List<Tag> getAll() {

        return baseMapper.selectList();
    }

    @Override
    public Object tagAdd(String name) {
        if(StrUtil.isBlank(name)) {
            return false;
        }
        String[] split = name.split(",");
        for (String tagName : split) {
            if(baseMapper.exist(tagName)>0)
            return 1;
        }
        for (String tagName : split) {
            Tag tag = new Tag();
            tag.setName(tagName);
            baseMapper.insert(tag);
        }
        return 0;
    }
}
