package com.example.websocket.ServiceImpl;

import com.example.websocket.Dao.AccountMapper;
import com.example.websocket.Dao.MessageMapper;
import com.example.websocket.PO.GrpMsg;
import com.example.websocket.PO.Invitation;
import com.example.websocket.PO.Message;
import com.example.websocket.PO.User;
import com.example.websocket.Service.MessageService;
import com.example.websocket.VO.GroupAndMsg;
import com.example.websocket.VO.InvitationVO;
import com.example.websocket.VO.ResponseVO;
import com.example.websocket.VO.UserAndMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    MessageMapper messageMapper;
    @Autowired
    AccountMapper accountMapper;
    @Override
    public ResponseVO getRecentMessages(int senderId, int receiverId){
        try {
            List<Message> messagesFromSender=messageMapper.getRecentMessages(senderId,receiverId);
            List<Message> messagesFromReceiver=messageMapper.getRecentMessages(receiverId,senderId);
            return ResponseVO.buildSuccess(sortMessages(messagesFromSender,messagesFromReceiver));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("error");
        }

    }

    @Override
    public ResponseVO getAllMessages(int senderId,int receiverId){
        try {
            List<Message> messagesFromSender=messageMapper.getAllMessages(senderId,receiverId);
            List<Message> messagesFromReceiver=messageMapper.getAllMessages(receiverId,senderId);
            return ResponseVO.buildSuccess(sortMessages(messagesFromSender,messagesFromReceiver));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("error");
        }

    }

    @Override
    public ResponseVO getRecent30Days(int userId){
        try{
            List<Message> messages  = messageMapper.getRecent30Days(userId);
            Map<Integer, UserAndMsg> users = new HashMap<>();
            for(Message message:messages){
                UserAndMsg from = users.get(message.getFrom_user());
                UserAndMsg to = users.get(message.getTo_user());
                if(users!=null&&((from!=null&&from.getUserId()!=userId)||(to!=null&&to.getUserId()!=userId))){
                    if(from!=null&&from.getUserId()!=userId){
                        from.addMsg(message);
                    }
                    else if(to!=null&&to.getUserId()!=userId){
                        to.addMsg(message);
                    }
                }else{
                    User tmp = new User();
                    if(message.getFrom_user()!=userId){
                        tmp = accountMapper.selectUserById(message.getFrom_user());
                    }else if(message.getTo_user()!=userId){
                        tmp = accountMapper.selectUserById(message.getTo_user());
                    }

                    UserAndMsg a = new UserAndMsg(tmp.getId(),tmp.getName());
                    users.put(tmp.getId(),a);
                    users.get(tmp.getId()).addMsg(message);
                }
            }
            return ResponseVO.buildSuccess(users);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("Get failed");
        }
    }

    @Override
    public ResponseVO getGroupRecent30Days(int userId){
        try{
            List<Integer> grps = messageMapper.getTeamIdsByUserId(userId);
            Map<Integer, GroupAndMsg> groupAndMsgs = new HashMap<>();
            for(int grpId:grps){
                List<GrpMsg> grpMsgs = messageMapper.getGroupRecent30Days(grpId);
                if(grpMsgs.size()!=0){
                    String grpname = grpMsgs.get(0).getTeamName();
                    GroupAndMsg tmp = new GroupAndMsg(grpId,grpname);
                    for(GrpMsg g:grpMsgs){
                        tmp.addMsg(g);
                    }
                    groupAndMsgs.put(grpId,tmp);
                }
            }
            return ResponseVO.buildSuccess(groupAndMsgs);
        }catch (Exception e){
            e.printStackTrace();
            return  ResponseVO.buildFailure("Get failed");
        }

    }

    @Override
    public ResponseVO getAllInvitations(int toUser){
        try{
            List<Invitation> invitations = messageMapper.getAllInvitations(toUser);
            List<InvitationVO> invitationVOS = new ArrayList<>();
            for(Invitation invitation:invitations){
                invitationVOS.add(new InvitationVO(invitation));
            }
            return ResponseVO.buildSuccess(invitationVOS);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("error");
        }
    }

    @Override
    public ResponseVO updateState(int senderId,int receiverId){
        try {
            messageMapper.updateState(senderId,receiverId);
            return ResponseVO.buildSuccess();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("error");
        }
    }

    @Override
    public ResponseVO updateInvitationState(int id,int state){
        try{
            messageMapper.updateInvitationState(id,state);
            return ResponseVO.buildSuccess();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("update failed");
        }
    }

    List<Message> sortMessages(List<Message> messagesFromSender,List<Message> messagesFromReceiver){
        List<Message> messages=new ArrayList<>();
        int senderSize=messagesFromSender.size(),receiverSize=messagesFromReceiver.size();
        int senderPtr=0,receiverPtr=0;
        while (senderPtr<senderSize && receiverPtr<receiverSize){
            if(messagesFromSender.get(senderPtr).getTime().after(messagesFromReceiver.get(receiverPtr).getTime())){
                messages.add(messagesFromSender.get(senderPtr));
                senderPtr++;
            }else{
                messages.add(messagesFromReceiver.get(receiverPtr));
                receiverPtr++;
            }
        }
        int ptr;
        List<Message> left;
        if(senderPtr>=senderSize){
            ptr=receiverPtr;
            left=messagesFromReceiver;
        }else{
            ptr=senderPtr;
            left=messagesFromSender;
        }
        while(ptr<left.size()){
            messages.add(left.get(ptr));
            ptr++;
        }
        return messages;
    }
}
