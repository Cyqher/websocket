package com.example.websocket.VO;

import com.example.websocket.PO.Invitation;

import java.sql.Timestamp;

public class InvitationVO {
    private int id;
    private String fromUsername;
    private int toUser;
    private int teamId;
    private String teamName;
    private int state;//0未读，1同意，2拒绝
    private Timestamp time;

    public InvitationVO(Invitation invitation){
        this.id = invitation.getId();
        this.fromUsername = invitation.getFromUsername();
        this.toUser = invitation.getToUser();
        this.teamId = invitation.getTeamId();
        this.teamName = invitation.getTeamName();
        this.state = invitation.getState();
        this.time = invitation.getTime();
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFromUsername() {
        return fromUsername;
    }

    public void setFromUsername(String fromUsername) {
        this.fromUsername = fromUsername;
    }

    public int getToUser() {
        return toUser;
    }

    public void setToUser(int toUser) {
        this.toUser = toUser;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}
