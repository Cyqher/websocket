package com.example.websocket.PO;

import com.example.websocket.VO.MessageForm;

import java.sql.Timestamp;

public class Message {
    private int id;
    private int from_user;
    private int to_user;
    private String content;
    //是否已读 0为未读 1为已读
    private int state;
    private Timestamp time;

    public Message(Integer id,String content,Integer from_user,Integer to_user,Integer state,Timestamp time){
        this.id=id;
        this.from_user=from_user;
        this.to_user=to_user;
        this.content=content;
        this.time=time;
    }

    public Message(String content,Integer from_user,Integer to_user,Integer state,Timestamp time){
        this.from_user=from_user;
        this.to_user=to_user;
        this.content=content;
        this.time=time;
    }
    public Message(MessageForm messageForm){
        this.content = messageForm.getContent();
        this.from_user = messageForm.getFromUser();
        this.to_user = messageForm.getToUser();
        this.state = 0;
        this.time = messageForm.getTimestamp();
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getFrom_user() {
        return from_user;
    }

    public int getTo_user() {
        return to_user;
    }

    public String getContent() {
        return content;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTo_user(int to_user) {
        this.to_user = to_user;
    }

    public void setFrom_user(int from_user) { this.from_user = from_user; }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Override
    public String toString(){
        return "from "+getFrom_user()+" to "+getTo_user()+" : "+getContent()+" "+getTime();
    }
}
