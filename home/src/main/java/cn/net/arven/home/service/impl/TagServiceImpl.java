package cn.net.arven.home.service.impl;

import cn.net.arven.common.entity.Tag;
import cn.net.arven.home.dao.TagDao;
import cn.net.arven.home.service.ITagService;
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
}
