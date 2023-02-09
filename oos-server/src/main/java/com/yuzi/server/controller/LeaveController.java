package com.yuzi.server.controller;

import com.yuzi.server.pojo.*;
import com.yuzi.server.service.ILeaveService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
@RequestMapping("/leave")
public class LeaveController {
    @Autowired
    private ILeaveService leaveService;

    @ApiOperation(value = "获取用户请假分页列表")
    @GetMapping("/")
    public RespPageBean getLeaveList(@RequestParam(defaultValue = "1") Integer currentPage,
                                     @RequestParam(defaultValue = "10") Integer size,
                                     String state,
                                     LocalDate[] beginDateScope){
        return leaveService.getLeaveList(currentPage,size,beginDateScope,state);
    }

    @ApiOperation(value = "获取部门树")
    @GetMapping("/deps")
    public List<Department> getDepartments(){
        return leaveService.getDepartments();
    }

    @ApiOperation(value = "获取当前用户所属员工")
    @GetMapping("/employee")
    public Employee getEmployee(){
        return leaveService.getEmployee();
    }

    @ApiOperation(value = "根据部门获取员工")
    @GetMapping("/depEmps")
    public List<Employee> getEmployeeByDepartment(Integer id){
        return leaveService.getEmployeeByDepartment(id);
    }

    @ApiOperation(value = "添加请假申请")
    @PostMapping("/")
    public RespBean addLeave(@RequestBody Leave leave){
        return leaveService.addLeave(leave);
    }

    @ApiOperation(value = "撤回请假申请")
    @DeleteMapping("/")
    public RespBean deleteLeave(Integer id){
        if(leaveService.removeById(id)){
            return RespBean.success("撤回成功");
        }else {
            return RespBean.error("操作失败！");
        }
    }

    @ApiOperation(value = "获取审核者请假申请")
    @GetMapping("/auditorLeave")
    public RespPageBean getAuditorLeave(@RequestParam(defaultValue = "1") Integer currentPage,
                                            @RequestParam(defaultValue = "10") Integer size,
                                            String state,
                                            LocalDate[] beginDateScope){
        return leaveService.getAuditorLeave(currentPage,size,state,beginDateScope);
    }

    @ApiOperation(value = "驳回申请")
    @PostMapping("/refuse")
    public  RespBean refuseTask(@RequestBody Leave leave){
        return leaveService.refuseLeave(leave);
    }

    @ApiOperation(value = "通过申请")
    @PostMapping("/pass")
    public RespBean passTask(@RequestBody Leave leave){
        return leaveService.passLeave(leave);
    }
}
