package com.example.websocket.PO;

import com.example.websocket.VO.InvitationForm;

import java.sql.Timestamp;

public class Invitation {
    private int id;
    private String fromUsername;
    private int toUser;
    private int teamId;
    private String teamName;
    private int state;//0未读，1同意，2拒绝
    private Timestamp time;

    public Invitation(int id, String fromUsername, int toUser, int teamId, String teamName, int state, Timestamp time) {
        this.id = id;
        this.fromUsername = fromUsername;
        this.toUser = toUser;
        this.teamId = teamId;
        this.teamName = teamName;
        this.state = state;
        this.time = time;
    }

    public Invitation(InvitationForm invitationForm){
        this.fromUsername = invitationForm.getFromUsername();
        this.toUser = invitationForm.getToUser();
        this.teamId = invitationForm.getTeamId();
        this.teamName = invitationForm.getTeamName();
        this.time = invitationForm.getTimestamp();
        this.state = 0;
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
