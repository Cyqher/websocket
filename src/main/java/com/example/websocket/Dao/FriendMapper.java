package com.example.websocket.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
@Service
public interface FriendMapper {
    public List<Integer> getFriendsId(int userId);

    public int addFriend(int userId,int friendId);

    public int deleteFriend(int userId,int friendId);

}
