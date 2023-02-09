package com.yuzi.server.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuzi.server.pojo.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author yuzi
 * @since 2023-01-06
 */
public interface EmployeeMapper extends BaseMapper<Employee> {

    /**
     * 获取所有员工(分页)
     *
     * @param page
     * @param employee
     * @param beginDateScope
     * @return
     */
    IPage<Employee> getEmployeeByPage(Page<Employee> page,
                                      @Param("employee") Employee employee,
                                      @Param("beginDateScope") LocalDate[] beginDateScope);

    /**
     * 获取员工账套
     *
     * @param page
     * @return
     */
    IPage<Employee> getEmployeeWithSalary(Page<Employee> page);

    /**
     * 获取一个员工信息
     *
     * @param eid
     * @return
     */
    Employee getEmployee(Integer eid);

    /**
     * 获取没有账号的员工列表
     * @return
     */
    List<Employee> getEmployeeWithNoUser();

    /**
     * 根据部门id获取员工
     * @param id
     * @return
     */
    List<Employee> getEmployeeByDepartment(Integer id);
}
