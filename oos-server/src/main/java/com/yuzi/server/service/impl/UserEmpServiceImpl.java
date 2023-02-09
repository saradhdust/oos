package com.yuzi.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuzi.server.mapper.EmployeeMapper;
import com.yuzi.server.mapper.UserMapper;
import com.yuzi.server.mapper.UserRoleMapper;
import com.yuzi.server.pojo.*;
import com.yuzi.server.service.IUserEmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author yuzi
 * @since 2023-01-06
 */
@Service
public class UserEmpServiceImpl implements IUserEmpService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    /**
     * 获取员工账号
     *
     * @param currentPage
     * @param size
     * @param workID
     * @param beginDateScope
     * @return
     */
    @Override
    public RespPageBean getUserList(Integer currentPage, Integer size, String workID, LocalDate[] beginDateScope) {
        List<User> users = userMapper.getUserListWithEmp((currentPage - 1) * size, size, workID, beginDateScope);
        long total = userMapper.selectCount(new QueryWrapper<User>());
        RespPageBean respPageBean = new RespPageBean(total, users);
        return respPageBean;
    }

    /**
     * 获取没有员工账号的员工列表
     *
     * @return
     */
    @Override
    public List<Employee> getEmployeeWithNoUser() {
        return employeeMapper.getEmployeeWithNoUser();
    }


    /**
     * 创建员工账号
     *
     * @param user
     * @param roles
     * @return
     */
    @Override
    @Transactional
    public RespBean createUser(User user, List<Integer> roles) {
        user.setEnabled(true);
        int result = userMapper.insert(user);
        User selectUser = userMapper.selectOne(new QueryWrapper<User>().eq("eid", user.getEid()));
        int result2 = userRoleMapper.addUserRoles(selectUser.getId(), roles.toArray(new Integer[0]));
        if (result != 0 && result2 != 0) {
            return RespBean.success("创建成功");
        }
        return RespBean.error("创建失败");
    }

    /**
     * 更新账号信息
     *
     * @param user
     * @param roles
     */
    @Override
    @Transactional
    public RespBean updateUser(User user, List<Integer> roles) {
        int result = userMapper.updateById(user);
        System.out.println("result1:" + result);
        if (result == 1) {
            userRoleMapper.delete(new QueryWrapper<UserRole>().eq("uid", user.getId()));
            Integer result2 = userRoleMapper.addUserRoles(user.getId(), roles.toArray(new Integer[0]));
            System.out.println("result2:" + result2);
            if (result2 != 0) {
                return RespBean.success("更新成功");
            }
        }
        return RespBean.error("更新失败");
    }

    /**
     * 删除账号信息
     *
     * @param id
     */
    @Override
    @Transactional
    public RespBean deleteUser(Integer id) {
        int result = userMapper.deleteById(id);
        if (result == 1) {
            int result2 = userRoleMapper.delete(new QueryWrapper<UserRole>().eq("uid", id));
            if(result2!=0){
                return RespBean.success("删除成功");
            }
        }
        return RespBean.error("删除失败");
    }
}
