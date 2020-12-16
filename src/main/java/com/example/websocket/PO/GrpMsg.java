package com.example.websocket.PO;

import com.example.websocket.VO.GrpMsgForm;

import java.sql.Timestamp;

public class GrpMsg {
    private int id;
    private int fromUser;
    private String fromUsername;
    private int teamId;
    private String teamName;
    private String content;
    //是否已读 0为未读 1为已读
    private Timestamp time;

    public GrpMsg(int id, String content, int fromUser, String fromUsername, int teamId, String teamName,Timestamp time) {
        this.id = id;
        this.fromUser = fromUser;
        this.fromUsername = fromUsername;
        this.teamId = teamId;
        this.teamName = teamName;
        this.content = content;
        this.time = time;
    }

    public GrpMsg(GrpMsgForm grpMsgForm){
        this.fromUser = grpMsgForm.getFromUser();
        this.fromUsername= grpMsgForm.getFromUsername();
        this.teamId = grpMsgForm.getGroupId();
        this.teamName = grpMsgForm.getGroupName();
        this.content  = grpMsgForm.getContent();
        this.time = grpMsgForm.getTimestamp();
    }

    public String getFromUsername() {
        return fromUsername;
    }

    public void setFromUsername(String fromUsername) {
        this.fromUsername = fromUsername;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFromUser() {
        return fromUser;
    }

    public void setFromUser(int fromUser) {
        this.fromUser = fromUser;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}
