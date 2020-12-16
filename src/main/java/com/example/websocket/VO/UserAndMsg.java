package com.example.websocket.VO;

import com.example.websocket.PO.Message;

import java.util.ArrayList;
import java.util.List;

public class UserAndMsg {
    private int userId;
    private String userName;
    private List<Message> msgs;

    public UserAndMsg(int userId, String userName) {
        this.userId = userId;
        this.userName = userName;
        this.msgs =  new ArrayList<>();
    }
    public void addMsg(Message message){
        this.msgs.add(message);
    }
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Message> getMsgs() {
        return msgs;
    }

    public void setMsgs(List<Message> msgs) {
        this.msgs = msgs;
    }
}
