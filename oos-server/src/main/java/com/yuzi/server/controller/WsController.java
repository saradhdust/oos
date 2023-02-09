package com.yuzi.server.controller;

import com.yuzi.server.pojo.Admin;
import com.yuzi.server.pojo.ChatMsg;
import com.yuzi.server.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

/**
 * websocket
 *
 * @author 星涯
 */
@Controller
public class WsController {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/websocket/chat")
    public void handleMsg(Authentication authentication, ChatMsg chatMsg) {
        User user = (User) authentication.getPrincipal();
        chatMsg.setFrom(user.getUsername());
        chatMsg.setFromNickName(user.getName());
        chatMsg.setDate(LocalDate.now());
        simpMessagingTemplate.convertAndSendToUser(chatMsg.getTo(), "/queue/chat", chatMsg);
    }
}
