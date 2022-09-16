package com.louis.rabc.module.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.louis.rabc.module.auth.dao.AuthDao;
import com.louis.rabc.module.auth.entity.Auth;
import com.louis.rabc.module.auth.service.AuthService;
import org.springframework.stereotype.Service;

/**
 * (Keys)表服务实现类
 *
 * @author 赖小燚
 * @since 2022-09-16 12:22:40
 */
@Service("keysService")
public class AuthServiceImpl extends ServiceImpl<AuthDao, Auth> implements AuthService {

}

