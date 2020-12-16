package com.example.websocket.VO;

import com.example.websocket.PO.GrpMsg;

import java.util.ArrayList;
import java.util.List;

public class GroupAndMsg {
    private int team_id;
    private String team_name;
    private List<GrpMsg> msgs;

    public GroupAndMsg(int tid,String tname){
        this.team_id = tid;
        this.team_name = tname;
        this.msgs = new ArrayList<>();
    }
    public void addMsg(GrpMsg msg){
        this.msgs.add(msg);
    }
    public int getTeam_id() {
        return team_id;
    }

    public void setTeam_id(int team_id) {
        this.team_id = team_id;
    }

    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

    public List<GrpMsg> getMsgs() {
        return msgs;
    }

    public void setMsgs(List<GrpMsg> msgs) {
        this.msgs = msgs;
    }
}
