package com.example.websocket.Service;

import com.example.websocket.VO.FriendVO;
import com.example.websocket.VO.ResponseVO;

import java.util.List;

public interface FriendService {
    public ResponseVO getFriends(int userId);

    public ResponseVO addFriend(int userId,int friendId);

    public ResponseVO deleteFriend(int userId,int friendId);
}
