package com.example.websocket.ServiceImpl;

import com.example.websocket.Dao.AccountMapper;
import com.example.websocket.Dao.FriendMapper;
import com.example.websocket.Dao.MessageMapper;
import com.example.websocket.PO.User;
import com.example.websocket.Service.FriendService;
import com.example.websocket.VO.FriendVO;
import com.example.websocket.VO.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class FriendServiceImpl implements FriendService {
    @Autowired
    FriendMapper friendMapper;
    @Autowired
    AccountMapper accountMapper;
    @Autowired
    MessageMapper messageMapper;

    @Override
    public ResponseVO getFriends(int userId){
        try {
            LinkedList<FriendVO> friendVOS=new LinkedList<>();
            List<Integer> friendIds=friendMapper.getFriendsId(userId);
            List<Integer> friendUnread=messageMapper.getUnreadSenderId(userId);
            FriendVO friendVO;
            for(int id:friendIds){
                friendVO=new FriendVO();
                friendVO.setFriendId(id);
                friendVO.setFriendName(accountMapper.selectUserById(id).getName());
                if(friendUnread.contains(id)){
                    friendVO.setFriendState(1);
                    friendVOS.addFirst(friendVO);
                }else{
                    friendVO.setFriendState(0);
                    friendVOS.addLast(friendVO);
                }
            }
            return ResponseVO.buildSuccess(friendVOS);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("error");
        }
    }

    @Override
    public ResponseVO addFriend(int userId,int friendId){
        try {
            User user=accountMapper.selectUserById(friendId);
            if(user==null){
                return ResponseVO.buildFailure("该账户不存在");
            }
            List<Integer> friendsId=friendMapper.getFriendsId(userId);
            if(friendsId.contains(friendId)){
                return ResponseVO.buildFailure("重复添加");
            }
            friendMapper.addFriend(userId,friendId);
            friendMapper.addFriend(friendId,userId);
            return ResponseVO.buildSuccess();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("error");
        }
    }

    @Override
    public ResponseVO deleteFriend(int userId,int friendId){
        try {
            friendMapper.deleteFriend(userId,friendId);
            friendMapper.deleteFriend(friendId,userId);
            return ResponseVO.buildSuccess();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("error");
        }
    }
}
