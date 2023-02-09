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
public interface ITaskService extends IService<Task>{
    /**
     * 获取申请的任务
     * @return
     */
    RespPageBean getTasks(Integer currentPage, Integer size, String title, LocalDate[] beginDateScope);

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
     * 增加新task
     * @param task
     */
    RespBean addTask(Task task);

    /**
     * 获取审核者
     * @param auditorWorkID
     * @return
     */
    Employee getAuditor(String auditorWorkID);


    /**
     * 获取审核任务
     * @param currentPage
     * @param size
     * @param title
     * @param state
     * @param beginDateScope
     * @return
     */
    RespPageBean getTasksWithApplier(Integer currentPage, Integer size, String title, String state, LocalDate[] beginDateScope);

    /**
     * 驳回申请
     * @param task
     * @return
     */
    RespBean refuseTask(Task task);

    /**
     * 通过申请
     * @param task
     * @return
     */
    RespBean passTask(Task task);

    /**
     * 获取财务部审核任务
     * @param currentPage
     * @param size
     * @param title
     * @param state
     * @param beginDateScope
     * @return
     */
    RespPageBean getFinTaskWithApplier(Integer currentPage, Integer size, String title, String state, LocalDate[] beginDateScope);
}
