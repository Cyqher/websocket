package com.example.websocket.Service;

import com.example.websocket.VO.ResponseVO;


public interface MessageService {
    public ResponseVO getRecentMessages(int senderId,int reveiverId);

    public ResponseVO getAllMessages(int senderId,int receiverId);

    public ResponseVO getRecent30Days(int userId);

    public ResponseVO getRecent(int userId);

    public ResponseVO getGroupRecent30Days(int userId);

    public ResponseVO getGroups(int userId);

    public ResponseVO getAllInvitations(int toUser);

    public ResponseVO updateState(int senderId,int receiverId);

    public ResponseVO updateInvitationState(int id,int state);

}
