package com.example.websocket.Dao;

import com.example.websocket.PO.GrpMsg;
import com.example.websocket.PO.Invitation;
import com.example.websocket.PO.Message;
import com.example.websocket.PO.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
@Service
public interface MessageMapper {

    public List<Message> getRecentMessages(int senderId,int receiverId);

    public List<Message> getAllMessages(int senderId,int receiverId);

    public List<Invitation> getAllInvitations(int toUser);

    public int addMessage(Message message);

    public int addTeamMessage(GrpMsg grpMsg);

    public int addTeamMessageState(int messageId,int userId,int teamId,int state);
    
    public int updateState(int senderId,int receiverId);

    public int updateTeamState(int teamId,int userId);

    public int updateInvitationState(int id,int newState);

    public List<Integer> getTeamIdsByUserId(int userId);

    public int sendInvitation(Invitation invitation);
    
    public List<Integer> getUnreadSenderId(int receiverId);

    public List<Message> getRecent30Days(int toUser);

    public List<GrpMsg> getGroupRecent30Days(int groupId);

}
