package com.louis.rabc.module.user.service.impl;

import com.louis.rabc.module.user.service.RolePermissionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RolePermissionServiceImplTest {

    @Autowired
    private RolePermissionService rolePermissionService;

    @Test
    void getPermissions() {
        rolePermissionService.getPermissions("[3,6]");
    }
}