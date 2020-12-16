package com.example.websocket.Dao;

import com.example.websocket.PO.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccountMapperTest {

    @Autowired
    AccountMapper accountMapper;

    @Test
    public void getNameByIdTest(){
        Assert.assertEquals("admin",accountMapper.selectUserById(1).getName());
    }

    @Test
    public void getUserByIdTest(){
        User user=accountMapper.selectUserById(1);
        Assert.assertEquals("admin",user.getName());
    }
}
