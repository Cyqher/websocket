package com.example.websocket.VO;

public class FriendVO {
    private int friendId;
    private String friendName;

    //是否有消息未读
    //0为没有 1为有
    private int friendState;

    public int getFriendId() {
        return friendId;
    }

    public int getFriendState() {
        return friendState;
    }

    public String getFriendName() {
        return friendName;
    }

    public void setFriendId(int friendId) {
        this.friendId = friendId;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }

    public void setFriendState(int friendState) {
        this.friendState = friendState;
    }

    @Override
    public String toString(){
        return getFriendId()+" "+getFriendName()+" "+getFriendState();
    }
}
