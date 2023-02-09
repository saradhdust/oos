package com.yuzi.server.controller;


import com.yuzi.server.pojo.Admin;
import com.yuzi.server.pojo.RespBean;
import com.yuzi.server.service.IAdminService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * 个人中心控制器
 *
 * @author 星涯
 */
@RestController
public class AdminInfoController {
    @Autowired
    private IAdminService adminService;

    @ApiOperation(value = "更新当前用户信息")
    @PutMapping("/admin/info")
    public RespBean updateAdminInfo(@RequestBody Admin admin, Authentication authentication) {
        if (adminService.updateById(admin)) {
            SecurityContextHolder.getContext()
                    .setAuthentication(new UsernamePasswordAuthenticationToken(admin, null, authentication.getAuthorities()));
            return RespBean.success("更新成功");
        } else {
            return RespBean.error("更新失败");
        }
    }

    @ApiOperation(value = "更新密码")
    @PutMapping("/admin/password")
    public RespBean updateAdminPassword(@RequestBody Map<String, Object> info) {
        String oldPassword = (String) info.get("oldPassword");
        String password = (String) info.get("password");
        Integer adminId = (Integer) info.get("adminId");
        return adminService.updateAdminPassword(adminId, oldPassword, password);
    }

    @ApiOperation(value = "更新头像")
    @PostMapping("/admin/userFace")
    public RespBean updateAdminUserFace(@RequestParam("file") MultipartFile file,
                                        @RequestParam("id") Integer id,
                                        Authentication authentication) {
        return adminService.updateAdminUserFace(file, id, authentication);
    }
}
