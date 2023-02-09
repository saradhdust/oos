package com.yuzi.server.controller;

import com.yuzi.server.pojo.RespBean;
import com.yuzi.server.pojo.User;
import com.yuzi.server.service.IUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yuzi
 * @since 2023-01-06
 */
@RestController
@RequestMapping("/userinfo")
public class UserInfoController {
    @Autowired
    IUserService userService;

    @ApiOperation(value = "个人中心获取用户信息")
    @GetMapping("/")
    public User getUserinfo(){
        return userService.getUserinfo();
    }

    @ApiOperation(value = "个人中心更新用户信息")
    @PutMapping("/")
    public RespBean updateUserinfo(@RequestBody User user, Authentication authentication){
        return userService.updateUserinfo(user,authentication);
    }

    @ApiOperation(value = "更新密码")
    @PutMapping("/password")
    public RespBean updateAdminPassword(@RequestBody Map<String, Object> info) {
        String oldPassword = (String) info.get("oldPassword");
        String password = (String) info.get("password");
        Integer adminId = (Integer) info.get("adminId");
        return userService.updateUserPassword(adminId, oldPassword, password);
    }

    @ApiOperation(value = "更新头像")
    @PostMapping("/userFace")
    public RespBean updateUserFace(@RequestParam("file") MultipartFile file,
                                        @RequestParam("id") Integer id,
                                        Authentication authentication) {
        return userService.updateUserFace(file, id, authentication);
    }
}
