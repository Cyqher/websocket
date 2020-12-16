package com.example.websocket.Controller;

import com.example.websocket.Service.FriendService;
import com.example.websocket.VO.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/friends")
public class FriendController {
    @Autowired
    FriendService friendService;

    @GetMapping("/all")
    //获得好友列表，放在一个list中，有未读消息的好友放在list前面
    public ResponseVO getFriends(@RequestParam("userId") int userId){
        return friendService.getFriends(userId);
    }

    @GetMapping("/add")
    //添加好友
    public ResponseVO addFriend(@RequestParam("userId") int userId,@RequestParam("friendId") int friendId){
        return friendService.addFriend(userId,friendId);
    }

    @GetMapping("delete")
    //删除好友
    public ResponseVO deleteFrined(@RequestParam("userId") int userId,@RequestParam("friendId") int friendId){
        return friendService.deleteFriend(userId,friendId);
    }
}
