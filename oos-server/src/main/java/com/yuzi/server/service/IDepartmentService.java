package com.yuzi.server.service;

import com.yuzi.server.pojo.Department;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yuzi.server.pojo.RespBean;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author yuzi
 * @since 2023-01-06
 */
public interface IDepartmentService extends IService<Department> {

    /**
     * 获取所有部门
     *
     * @return
     */
    List<Department> getAllDepartments();

    /**
     * 添加部门
     * @param department
     * @return
     */
    RespBean addDepartment(Department department);

    /**
     * 删除部门
     * @param id
     * @return
     */
    RespBean deleteDepartment(Integer id);
}
