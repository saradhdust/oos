package com.yuzi.server.service;

import com.yuzi.server.pojo.Employee;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yuzi.server.pojo.RespBean;
import com.yuzi.server.pojo.RespPageBean;

import java.time.LocalDate;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author yuzi
 * @since 2023-01-06
 */
public interface IEmployeeService extends IService<Employee> {

    /**
     * 获取所有员工(分页)
     *
     * @param currentPage
     * @param size
     * @param employee
     * @param beginDateScope
     * @return
     */
    RespPageBean getEmployeeByPage(Integer currentPage, Integer size, Employee employee, LocalDate[] beginDateScope);

    /**
     * 获取新工号
     *
     * @return
     */
    RespBean getMaxWorkID();

    /**
     * 添加新员工
     *
     * @param employee
     * @return
     */
    RespBean addEmployee(Employee employee);

    /**
     * 更新员工
     *
     * @param employee
     * @return
     */
    RespBean updateEmployee(Employee employee);

    /**
     * 获取所有工资账套
     * @param currentPage
     * @param size
     * @return
     */
    RespPageBean getEmployeeWithSalary(Integer currentPage, Integer size);


    /**
     * @param id
     * @return
     */
    RespBean deleteEmployee(Integer id);
}
