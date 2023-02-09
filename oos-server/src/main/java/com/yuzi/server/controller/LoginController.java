package com.yuzi.server.controller;

import com.yuzi.server.pojo.*;
import com.yuzi.server.service.IAdminService;
import com.yuzi.server.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;


/**
 * 登录
 *
 * @author 星涯
 */
@Api("LoginController")
@RestController
public class LoginController {
    @Autowired
    private IAdminService adminService;

    @Autowired
    private IUserService userService;

//    @ApiOperation(value = "登录")
//    @PostMapping("/login")
//    public RespBean login(@RequestBody AdminLoginParam adminLoginParam, HttpServletRequest request) {
//        System.out.println(adminLoginParam.getUsername()+"    "+adminLoginParam.getPassword());
//        return adminService.login(adminLoginParam.getUsername(), adminLoginParam.getPassword());
//    }

    @ApiOperation(value = "登录(新)")
    @PostMapping("/login")
    public RespBean login(@RequestBody UserLoginParam userLoginParam, HttpServletRequest request) {
        System.out.println(userLoginParam.getUsername()+"    "+userLoginParam.getPassword());
        return userService.login(userLoginParam.getUsername(), userLoginParam.getPassword());
    }

//    @ApiOperation(value = "获取当前登录用户信息")
//    @GetMapping("/admin/info")
//    public Admin getAdminInfo(Principal principal) {
//        if (null == principal) {
//            return null;
//        }
//        String username = principal.getName();
//        Admin admin = adminService.getAdminByUsername(username);
//        admin.setPassword(null);
//        admin.setRoles(adminService.getRolesByAdminId(admin.getId()));
//        return admin;
//    }

    @ApiOperation(value = "获取当前登录用户信息(新)")
    @GetMapping("/user/info")
    public User getUserInfo(Principal principal) {
        if (null == principal) {
            return null;
        }
        String username = principal.getName();
        User user = userService.getUserByUsername(username);
        user.setPassword(null);
        user.setRoles(userService.getRolesByUid(user.getId()));
        return user;
    }

    @ApiOperation(value = "退出登录")
    @PostMapping("/logout")
    public RespBean logout() {
        return RespBean.success("注销成功");
    }
}
