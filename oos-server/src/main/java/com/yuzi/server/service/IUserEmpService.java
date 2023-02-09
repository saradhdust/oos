package com.yuzi.server.service;

import com.yuzi.server.pojo.Employee;
import com.yuzi.server.pojo.RespBean;
import com.yuzi.server.pojo.RespPageBean;
import com.yuzi.server.pojo.User;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author yuzi
 * @since 2023-01-06
 */
public interface IUserEmpService {


    /**
     * 获取员工账号
     *
     * @param currentPage
     * @param size
     * @param workID
     * @param beginDateScope
     * @return
     */
    RespPageBean getUserList(Integer currentPage, Integer size, String workID, LocalDate[] beginDateScope);

    /**
     * 获取没有员工账号的员工列表
     * @return
     */
    List<Employee> getEmployeeWithNoUser();

    /**
     * 创建员工账号
     * @param user
     * @param roles
     * @return
     */
    RespBean createUser(User user, List<Integer> roles);


    /**
     * 更新账号信息
     * @param user
     * @param roles
     */
    RespBean updateUser(User user, List<Integer> roles);

    /**
     * 删除账号信息
     * @param id
     */
    RespBean deleteUser(Integer id);
}
