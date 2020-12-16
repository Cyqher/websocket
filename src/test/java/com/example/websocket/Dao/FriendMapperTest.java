package com.example.websocket.Dao;

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
public class FriendMapperTest {

    @Autowired
    FriendMapper friendMapper;

    @Test
    public void getFriendsTest(){
        Assert.assertEquals(2,friendMapper.getFriendsId(1).size());
    }

    @Test
    public void deleteFriendTest(){
        friendMapper.deleteFriend(1,3);
        Assert.assertEquals(1,friendMapper.getFriendsId(1).size());
    }

    @Test
    public void addFriendTest(){
        friendMapper.addFriend(1,4);
        Assert.assertEquals(3,friendMapper.getFriendsId(1).size());
    }
}
