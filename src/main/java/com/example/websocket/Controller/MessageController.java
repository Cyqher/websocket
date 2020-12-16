package com.example.websocket.Controller;

import com.example.websocket.Config.WebSocket;
import com.example.websocket.Service.MessageService;
import com.example.websocket.VO.GrpMsgForm;
import com.example.websocket.VO.InvitationForm;
import com.example.websocket.VO.MessageForm;
import com.example.websocket.VO.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {
    @Autowired
    MessageService messageService;
    @Autowired
    WebSocket webSocket;
    @GetMapping("/recent")
    //获得与好友的最近聊天记录，不超过10条
    public ResponseVO getRecentMessages(@RequestParam("senderId") int senderId,@RequestParam("receiverId") int receiverId){
        return messageService.getRecentMessages(senderId,receiverId);
    }

    @GetMapping("/all")
    //获得所有聊天记录
    public ResponseVO getAllMessages(@RequestParam("senderId") int senderId,@RequestParam("receiverId") int receiverId){
        return messageService.getAllMessages(senderId,receiverId);
    }

    @GetMapping("/30d")
    public ResponseVO getRecent30Days(@RequestParam("userId")int userId){
        return messageService.getRecent30Days(userId);
    }

    @GetMapping("/group30d")
    public ResponseVO getGroupRecent30Days(@RequestParam("userId")int userId){
        return messageService.getGroupRecent30Days(userId);
    }

    @GetMapping("/allInvitation")
    public ResponseVO getAllInvitations(@RequestParam("toUser")int toUser){
        return messageService.getAllInvitations(toUser);
    }

    @GetMapping("/update")
    //更新聊天记录，将所有未读记录更新为已读
    public ResponseVO updateReadState(@RequestParam("senderId") int senderId,@RequestParam("receiverId") int receiverId){
        return messageService.updateState(senderId,receiverId);
    }

    @GetMapping("/updateInvitation")
    //更新聊天记录，将所有未读记录更新为已读
    public ResponseVO updateInvitationState(@RequestParam("id") int id,@RequestParam("state") int state){
        return messageService.updateInvitationState(id,state);
    }


    @PostMapping("/sendIndMsg")
    public ResponseVO sendIndividualMessage(@RequestBody MessageForm messageForm) throws IOException {
        return webSocket.sendIndividualMessage(messageForm);
    }

    @PostMapping("/sendGrpMsg")
    public ResponseVO sendGroupMessage(@RequestBody GrpMsgForm grpMsgForm) throws IOException {
        return webSocket.sendGroupMessage(grpMsgForm);
    }

    @PostMapping("/invite")
    public ResponseVO sendInvitation(@RequestBody InvitationForm invitationForm){
        return webSocket.sendInvitation(invitationForm);
    }
}
