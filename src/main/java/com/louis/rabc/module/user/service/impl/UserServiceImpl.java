package com.louis.rabc.module.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.louis.rabc.module.user.dao.UserDao;
import com.louis.rabc.module.user.entity.User;
import com.louis.rabc.module.user.service.UserService;
import org.springframework.stereotype.Service;

/**
 * (User)表服务实现类
 *
 * @author 赖小燚
 * @since 2022-09-14 21:13:23
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

}
