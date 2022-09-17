package com.louis.rabc.module.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.louis.rabc.module.user.entity.User;

/**
 * (User)表服务接口
 *
 * @author 赖小燚
 * @since 2022-09-14 21:13:23
 */
public interface UserService extends IService<User> {

    void createUser(String username, String phone, String passwordDigest);
}

