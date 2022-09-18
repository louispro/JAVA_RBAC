package com.louis.rabc.module.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.louis.rabc.module.user.dao.ResourceDao;
import com.louis.rabc.module.user.entity.Resource;
import com.louis.rabc.module.user.service.ResourceService;
import org.springframework.stereotype.Service;

/**
 * (Resource)表服务实现类
 *
 * @author 赖小燚
 * @since 2022-09-18 17:23:03
 */
@Service("resourceService")
public class ResourceServiceImpl extends ServiceImpl<ResourceDao, Resource> implements ResourceService {

}

