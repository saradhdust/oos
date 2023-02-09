package com.yuzi.server.controller;


import com.yuzi.server.pojo.*;
import com.yuzi.server.service.ITaskService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private ITaskService taskService;

    @ApiOperation(value = "获取所有本员工的任务")
    @GetMapping("/")
    public RespPageBean getTasks(@RequestParam(defaultValue = "1") Integer currentPage,
                                 @RequestParam(defaultValue = "10") Integer size,
                                 String title,
                                 LocalDate[] beginDateScope){
        return taskService.getTasks(currentPage,size,title,beginDateScope);
    }

    @ApiOperation(value = "获取部门树")
    @GetMapping("/deps")
    public List<Department> getDepartments(){
        return taskService.getDepartments();
    }

    @ApiOperation(value = "获取当前用户所属员工")
    @GetMapping("/employee")
    public Employee getEmployee(){
        return taskService.getEmployee();
    }

    @ApiOperation(value = "根据部门获取员工")
    @GetMapping("/depEmps")
    public List<Employee> getEmployeeByDepartment(Integer id){
        return taskService.getEmployeeByDepartment(id);
    }

    @ApiOperation(value = "增加新task")
    @PostMapping("/")
    public RespBean addTask(@RequestBody Task task){
        return taskService.addTask(task);
    }

    @ApiOperation(value = "获取审核者")
    @GetMapping("/auditor")
    public Employee getAuditor(String auditorWorkID){
        return taskService.getAuditor(auditorWorkID);
    }

    @ApiOperation(value = "撤回task")
    @DeleteMapping("/")
    public RespBean deleteTask(Integer id){
        if(taskService.removeById(id)){
            return RespBean.success("撤回成功");
        }else {
            return RespBean.error("操作失败！");
        }
    }

    @ApiOperation(value = "获取审核者任务")
    @GetMapping("/auditorTask")
    public RespPageBean getTasksWithApplier(@RequestParam(defaultValue = "1") Integer currentPage,
                                            @RequestParam(defaultValue = "10") Integer size,
                                            String title,
                                            String state,
                                            LocalDate[] beginDateScope){
        return taskService.getTasksWithApplier(currentPage,size,title,state,beginDateScope);
    }

    @ApiOperation(value = "驳回申请")
    @PostMapping("/refuse")
    public  RespBean refuseTask(@RequestBody Task task){
        return taskService.refuseTask(task);
    }

    @ApiOperation(value = "通过申请")
    @PostMapping("/pass")
    public RespBean passTask(@RequestBody Task task){
        return taskService.passTask(task);
    }

    @ApiOperation(value = "获取财务部审核任务")
    @GetMapping("/finTask")
    public RespPageBean getFinTaskWithApplier(@RequestParam(defaultValue = "1") Integer currentPage,
                                              @RequestParam(defaultValue = "10") Integer size,
                                              String title,
                                              String state,
                                              LocalDate[] beginDateScope){
        RespPageBean finTaskWithApplier = taskService.getFinTaskWithApplier(currentPage, size, title, state, beginDateScope);
        return finTaskWithApplier;
    }
}
