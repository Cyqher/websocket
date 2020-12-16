package com.example.websocket.Config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.websocket.Dao.AccountMapper;
import com.example.websocket.Dao.MessageMapper;
import com.example.websocket.PO.GrpMsg;
import com.example.websocket.PO.Invitation;
import com.example.websocket.PO.Message;
import com.example.websocket.PO.User;
import com.example.websocket.VO.GrpMsgForm;
import com.example.websocket.VO.InvitationForm;
import com.example.websocket.VO.MessageForm;
import com.example.websocket.VO.ResponseVO;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.swing.*;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Component
@ServerEndpoint("/websocket/{userId}")
public class WebSocket {
    @Autowired
    AccountMapper accountMapperCore;
    @Autowired
    MessageMapper messageMapperCore;
    static AccountMapper accountMapper;
    static MessageMapper messageMapper;

    @PostConstruct
    public void initMapper(){
        accountMapper=accountMapperCore;
        messageMapper=messageMapperCore;
    }
    /**
     * 在线人数
     */
    public static int onlineNumber = 0;
    /**
     * 以用户的id为key，WebSocket为对象保存起来
     */
    private static Map<Integer, WebSocket> clients = new ConcurrentHashMap<Integer, WebSocket>();
    /**
     * 会话
     */
    private Session session;
    /**
     * 用户id
     */
    private int userId;
    /**
     * 建立连接
     *
     * @param session
     */
    @OnOpen
    public void onOpen(@PathParam("userId") int userId, Session session)
    {
        onlineNumber++;
        System.out.println("连接的客户id："+session.getId()+"用户Id："+userId);
        this.userId = userId;
        this.session = session;
        System.out.println("有新连接加入！ 当前在线人数" + onlineNumber);
        clients.put(userId, this);
//        try {
//            //messageType 1代表上线 2代表下线 3代表在线名单 4代表普通消息
//            //先给所有人发送通知，说我上线了
//            Map<String,Object> map1 = new HashMap<String,Object>();
//            map1.put("messageType",1);
//            map1.put("username",username);
//            sendMessageAll(JSON.toJSONString(map1),username);
//
//            //给自己发一条消息：告诉自己现在都有谁在线
//            Map<String,Object> map2 = new HashMap<String,Object>();
//            map2.put("messageType",3);
//            //移除掉自己
//            Set<String> set = clients.keySet();
//            map2.put("onlineUsers",set);
//            sendMessageTo(JSON.toJSONString(map2),username);
//        }
//        catch (IOException e){
//            System.out.println(username+"上线的时候通知所有人发生了错误");
//        }



    }

    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("服务端发生了错误"+error.getMessage());
        //error.printStackTrace();
    }
    /**
     * 连接关闭
     */
    @OnClose
    public void onClose()
    {
        onlineNumber--;
        //webSockets.remove(this);
        clients.remove(userId);
//        try {
//            //messageType 1代表上线 2代表下线 3代表在线名单  4代表普通消息
//            Map<String,Object> map1 = new HashMap<String,Object>();
//            map1.put("messageType",2);
//            map1.put("onlineUsers",clients.keySet());
//            map1.put("username",username);
//            sendMessageAll(JSON.toJSONString(map1),username);
//        }
//        catch (IOException e){
//            System.out.println(username+"下线的时候通知所有人发生了错误");
//        }
        System.out.println("有连接关闭！ 当前在线人数" + onlineNumber);
    }

    /**
     * 收到客户端的消息
     *
     * @param message 消息
     * @param session 会话
     */
    @OnMessage
    public void onMessage(String message, Session session)
    {
        try {
            //System.out.println("来自客户端消息：" + message+"客户端的id是："+session.getId());

        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("发生了错误");
        }

    }


    public ResponseVO sendIndividualMessage(MessageForm messageForm) throws IOException {

        try{
            messageForm.setTimestamp(new Timestamp(System.currentTimeMillis()));
            messageMapper.addMessage(new Message(messageForm));
            if(clients.get(messageForm.getToUser())!=null) {
                clients.get(messageForm.getToUser()).session.getAsyncRemote().sendText(JSON.toJSONString(messageForm));
            }
            return ResponseVO.buildSuccess("Send successfully");

        }catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("Send failed");

        }

    }
    public ResponseVO sendGroupMessage(GrpMsgForm grpMsgForm) throws IOException{
        try{
            List<User> users = accountMapper.getUsersByTeamId(grpMsgForm.getGroupId());

            grpMsgForm.setTimestamp(new Timestamp(System.currentTimeMillis()));
            GrpMsg msg = new GrpMsg(grpMsgForm);
            messageMapper.addTeamMessage(msg);
            for(User user:users){
                if(user.getId()!=grpMsgForm.getFromUser()) {
//                    messageMapper.addTeamMessageState(msg.getId(), user.getId(), grpMsgForm.getGroupId(), 0);
                    if(clients.get(user.getId())!=null){
                        clients.get(user.getId()).session.getAsyncRemote().sendText(JSON.toJSONString(grpMsgForm));
                    }
                }
            }
            return ResponseVO.buildSuccess("Send successfully");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("Send failed");
        }
    }
    public ResponseVO sendInvitation(InvitationForm invitationForm){
        try{
            invitationForm.setTimestamp(new Timestamp(System.currentTimeMillis()));
            Invitation invitation = new Invitation(invitationForm);
            messageMapper.sendInvitation(invitation);
            if(clients.get(invitation.getToUser())!=null) {
                clients.get(invitation.getToUser()).session.getAsyncRemote().sendText(JSON.toJSONString(invitationForm));
            }
            return ResponseVO.buildSuccess("Invite successfully");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("Invitation failed");
        }
    }

//    public void sendMessageAll(String message,String FromUserName) throws IOException {
//        /**
//         * 发送个组员
//         */
//    }

    public static synchronized int getOnlineCount() {
        return onlineNumber;
    }

}
