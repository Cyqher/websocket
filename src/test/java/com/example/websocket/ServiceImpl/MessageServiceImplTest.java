package com.example.websocket.ServiceImpl;


import com.example.websocket.PO.Message;
import com.example.websocket.Service.MessageService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MessageServiceImplTest {
    @Autowired
    MessageService messageService;

    @Test
    public void getRecentTest(){
        List<Message> messages=(List<Message>) messageService.getRecentMessages(1,2).getContent();
        boolean ans=true;
        for(int i=0;i<messages.size()-1;i++){
            ans=ans&&(messages.get(i).getTime().after(messages.get(i+1).getTime()));
        }
        Assert.assertTrue(ans);
        for(Message message:messages){
            System.out.println(message.toString());
        }
    }

    @Test
    public void getAllTest(){
        List<Message> messages=(List<Message>) messageService.getAllMessages(1,2).getContent();
        boolean ans=true;
        for(int i=0;i<messages.size()-1;i++){
            ans=ans&&(messages.get(i).getTime().after(messages.get(i+1).getTime()));
        }
        Assert.assertTrue(ans);
        for(Message message:messages){
            System.out.println(message.toString());
        }

    }
}
