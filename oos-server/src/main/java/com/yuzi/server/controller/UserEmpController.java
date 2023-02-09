package com.yuzi.server.controller;

import com.yuzi.server.pojo.Employee;
import com.yuzi.server.pojo.RespBean;
import com.yuzi.server.pojo.RespPageBean;
import com.yuzi.server.pojo.User;
import com.yuzi.server.service.IUserEmpService;
import com.yuzi.server.service.impl.UserEmpServiceImpl;
import io.netty.util.concurrent.SucceededFuture;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author yuzi
 * @since 2023-01-06
 */
@RestController
@RequestMapping("/userEmp")
public class UserEmpController {
    @Autowired
    private IUserEmpService userEmpService;

    @ApiOperation(value = "获取员工账号列表")
    @GetMapping("/")
    public RespPageBean getUserList(@RequestParam(defaultValue = "1") Integer currentPage,
                                    @RequestParam(defaultValue = "10") Integer size,
                                    String workID,
                                    LocalDate[] beginDateScope){
        return userEmpService.getUserList(currentPage, size, workID, beginDateScope);
    }

    @ApiOperation(value = "获取没有账号的员工列表")
    @GetMapping("/nouser")
    public List<Employee> getEmployeeWithNoUser(){
        return userEmpService.getEmployeeWithNoUser();
    }

    @ApiOperation(value = "创建账号")
    @PostMapping("/create")
    public RespBean createUser(@RequestBody Map<String,Object> user){
        String username = (String) user.get("username");
        String name = (String)user.get("name");
        String password = (String)user.get("password");
        Integer eid = (Integer) user.get("eid");
        List<Integer> roles =(List<Integer>) user.get("roles");
        User newUser=new User();
        newUser.setPassword(password);
        newUser.setName(name);
        newUser.setEid(eid);
        newUser.setUsername(username);
        return userEmpService.createUser(newUser,roles);
    }

    @ApiOperation(value = "更新用户信息")
    @PutMapping("/")
    public RespBean updateUser(@RequestBody Map<String,Object> info){
        User user=new User();
        user.setId((Integer) info.get("id"));
        user.setUsername((String) info.get("username"));
        user.setPassword((String) info.get("password"));
        user.setEnabled(true);
        List<Integer> roles = (List<Integer>) info.get("roles");
        return userEmpService.updateUser(user,roles);
    }

    @ApiOperation(value = "删除用户账号")
    @DeleteMapping("/{id}")
    public RespBean deleteUser(@PathVariable Integer id){
        return userEmpService.deleteUser(id);
    }
}
