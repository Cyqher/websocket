package com.example.websocket.Dao;

import com.example.websocket.PO.Message;
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
public class MessageMapperTest {
    @Autowired
    MessageMapper messageMapper;

    @Test
    public void getRecentTest(){

        List<Message> messages=messageMapper.getRecentMessages(1,2);
        Assert.assertEquals(5,messages.size());
        for(Message message:messages){
            System.out.println(message.toString());
        }
    }

    @Test
    public void addTest(){
        List<Message> messages=messageMapper.getAllMessages(2,1);
        Message message=new Message("add",2,1,1,new Timestamp(System.currentTimeMillis()));
        int size=messages.size();
        messageMapper.addMessage(message);
        messages=messageMapper.getAllMessages(2,1);
        Assert.assertEquals(size+1,messages.size());
        for(Message m:messages){
            System.out.println(m.toString());
        }
    }

    @Test
    public void selectUnreadTest(){
        List<Integer> unreadUserId=messageMapper.getUnreadSenderId(2);
        Assert.assertEquals(1,unreadUserId.size());
    }

    @Test
    public void updateStateTest(){
        messageMapper.updateState(1,2);
        Assert.assertEquals(0,messageMapper.getUnreadSenderId(2).size());
    }


}
