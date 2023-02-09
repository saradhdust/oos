package com.yuzi.server.mapper;

import com.yuzi.server.pojo.Department;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author yuzi
 * @since 2023-01-06
 */
public interface DepartmentMapper extends BaseMapper<Department> {

    /**
     * 获取所有部门
     *
     * @param parentId
     * @return
     */
    List<Department> getAllDepartments(Integer parentId);


    /**
     * 添加部门
     *
     * @param department
     */
    void addDepartment(Department department);

    /**
     * 删除部门
     * @param department
     */
    void deleteDepartment(Department department);
}
