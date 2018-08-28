package com.spring.study.service.impl;

import com.spring.study.SpringStudyServiceApplication;
import com.spring.study.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author: ZhouMingming
 * @data: Create on 2018/7/30.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {SpringStudyServiceApplication.class, UserServiceImplTest.class})
public class UserServiceImplTest {

    @Autowired
    private UserService userService;
}
