package cn.net.arven.home.service;

import cn.net.arven.common.entity.Tag;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2021-03-14
 */
public interface ITagService extends IService<Tag> {


    List<Tag> getAll();


    Object tagAdd(String name);
}
