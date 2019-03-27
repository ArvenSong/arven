package cn.net.arven.tally.service.impl;

import cn.net.arven.tally.dao.UserDao;
import cn.net.arven.tally.entity.User;
import cn.net.arven.tally.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 
 * @since 2018-12-13
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements IUserService {

}
