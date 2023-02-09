package com.yuzi.server.controller;

import com.yuzi.server.pojo.Admin;
import com.yuzi.server.pojo.User;
import com.yuzi.server.service.IAdminService;
import com.yuzi.server.service.IUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 在线聊天
 *
 * @author 星涯
 */
@RestController
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    private IAdminService adminService;

    @Autowired
    private IUserService userService;

    @ApiOperation(value = "获取所有操作员")
    @GetMapping("/user")
    public List<User> getAllAdmin(String keywords) {
        return userService.getAllUsers(keywords);
    }
}
