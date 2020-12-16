package com.example.websocket.ServiceImpl;

import com.example.websocket.Dao.FriendMapper;
import com.example.websocket.Dao.MessageMapper;
import com.example.websocket.PO.Message;
import com.example.websocket.Service.FriendService;
import com.example.websocket.VO.FriendVO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FriendServiceImplTest {
    @Autowired
    FriendService friendService;
    @Autowired
    MessageMapper messageMapper;

    @Test
    public void deleteFriendTest(){
        friendService.deleteFriend(1,2);
        List<FriendVO> friendVOS=(List<FriendVO>) friendService.getFriends(1).getContent();
        Assert.assertEquals(1,friendVOS.size());
    }

    @Test
    public void addFriendTest(){
        friendService.addFriend(1,4);
        List<FriendVO> friendVOS;
        friendVOS=(List<FriendVO>) friendService.getFriends(1).getContent();
        Assert.assertEquals(3,friendVOS.size());
        friendVOS=(List<FriendVO>) friendService.getFriends(4).getContent();
    }

    @Test
    public void getFriendsTest(){
        Message message=new Message("add",3,1,1,new Timestamp(System.currentTimeMillis()));
        Message message2=new Message("add2",4,1,0,new Timestamp(System.currentTimeMillis()));
        messageMapper.addMessage(message);
        messageMapper.addMessage(message2);
        friendService.addFriend(1,4);
        List<FriendVO> friendVOS=(List<FriendVO>) friendService.getFriends(1).getContent();
        Assert.assertEquals(3,friendVOS.size());
        for(FriendVO friendVO:friendVOS){
            System.out.println(friendVO.toString());
        }
    }
}
