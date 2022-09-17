package com.louis.rabc.utils;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TestUtilTest {

    @Autowired
    private TestUtil testUtil;

    @Test
    void loginEncrypt() {
        testUtil.loginEncrypt();
    }
}