package com.yuzi.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yuzi.server.pojo.*;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yuzi
 * @since 2023-01-06
 */
public interface ILeaveService extends IService<Leave> {

    /**
     * 获取请假列表
     *
     * @param currentPage
     * @param size
     * @param beginDateScope
     * @param state
     * @return
     */
    RespPageBean getLeaveList(Integer currentPage, Integer size, LocalDate[] beginDateScope, String state);

    /**
     * 获取所有部门树
     * @return
     */
    List<Department> getDepartments();

    /**
     * 获取当前登录用户所属员工
     * @return
     */
    Employee getEmployee();

    /**
     * 根据部门获取员工
     * @param id
     * @return
     */
    List<Employee> getEmployeeByDepartment(Integer id);

    /**
     * 添加新请假申请
     * @param leave
     * @return
     */
    RespBean addLeave(Leave leave);

    /**
     * 获取审核者请假申请
     * @param currentPage
     * @param size
     * @param state
     * @param beginDateScope
     * @return
     */
    RespPageBean getAuditorLeave(Integer currentPage, Integer size, String state, LocalDate[] beginDateScope);

    /**
     * 拒绝请假
     * @param leave
     * @return
     */
    RespBean refuseLeave(Leave leave);

    /**
     * 通过请假申请
     * @param leave
     * @return
     */
    RespBean passLeave(Leave leave);
}
