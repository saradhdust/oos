package com.yuzi.server.controller;

import com.yuzi.server.pojo.RespBean;
import com.yuzi.server.pojo.Role;
import com.yuzi.server.pojo.User;
import com.yuzi.server.service.IRoleService;
import com.yuzi.server.service.IUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author yuzi
 * @since 2023-01-06
 */
@RestController
@RequestMapping("/system/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @ApiOperation(value = "获取所有操作员(新)")
    @GetMapping("/")
    public List<User> getAllUsers(String keywords){
        return userService.getAllUsers(keywords);
    }

    @ApiOperation(value = "更新操作员(新)")
    @PutMapping("/")
    public RespBean updateUser(@RequestBody User user){
        if(userService.updateById(user)){
            return RespBean.success("更新成功");
        }else {
            return RespBean.error("更新失败");
        }
    }

    @ApiOperation(value = "删除操作员(新)")
    @DeleteMapping("/{id}")
    public RespBean deleteUser(@PathVariable Integer id){
        if(userService.removeById(id)){
            return RespBean.success("删除成功");
        } else {
            return RespBean.error("删除失败");
        }
    }

    @ApiOperation(value = "获取角色列表(新)")
    @GetMapping("/roles")
    public List<Role> getAllRoles(){
        return roleService.list();
    }

    @ApiOperation(value = "更新用户角色(新)")
    @PutMapping("/role")
    public RespBean updateUserRole(Integer uid, Integer[] rids){
        return userService.updateUserRole(uid,rids);
    }
}
