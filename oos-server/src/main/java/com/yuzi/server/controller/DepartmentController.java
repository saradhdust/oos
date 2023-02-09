package com.yuzi.server.controller;


import com.google.common.collect.MultimapBuilder;
import com.yuzi.server.pojo.Department;
import com.yuzi.server.pojo.RespBean;
import com.yuzi.server.service.IDepartmentService;
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
@RequestMapping("/system/basic/department")
public class DepartmentController {
    @Autowired
    IDepartmentService departmentService;

    @ApiOperation(value = "获取所有部门")
    @GetMapping("/")
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @ApiOperation(value = "添加部门")
    @PostMapping("/")
    public RespBean addDepartment(@RequestBody Department department) {
        return departmentService.addDepartment(department);
    }

    @ApiOperation(value = "删除部门")
    @DeleteMapping("/{id}")
    public RespBean deleteDepartment(@PathVariable Integer id) {
        return departmentService.deleteDepartment(id);
    }
}
