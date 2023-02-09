package com.yuzi.server.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuzi.server.mapper.DepartmentMapper;
import com.yuzi.server.mapper.EmployeeMapper;
import com.yuzi.server.mapper.JoblevelMapper;
import com.yuzi.server.mapper.LeaveMapper;
import com.yuzi.server.pojo.*;
import com.yuzi.server.service.IJoblevelService;
import com.yuzi.server.service.ILeaveService;
import com.yuzi.server.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yuzi
 * @since 2023-01-06
 */
@Service
public class LeaveServiceImpl extends ServiceImpl<LeaveMapper, Leave> implements ILeaveService {
    @Autowired
    private LeaveMapper leaveMapper;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private DepartmentMapper departmentMapper;

    /**
     * 获取请假列表
     *
     * @param currentPage
     * @param size
     * @param beginDateScope
     * @param state
     * @return
     */
    @Override
    public RespPageBean getLeaveList(Integer currentPage, Integer size, LocalDate[] beginDateScope, String state) {
        Integer eid = UserUtil.getCurrentUser().getEid();
        Employee employee = employeeMapper.selectById(eid);
        String workID = employee.getWorkID();
        Page<Leave> page=new Page<>(currentPage,size);
        IPage<Leave> iPage=leaveMapper.getLeaveList(page,workID,state,beginDateScope);
        return new RespPageBean(iPage.getTotal(), iPage.getRecords());
    }

    /**
     * 获取所有部门树
     * @return
     */
    @Override
    public List<Department> getDepartments() {
        return departmentMapper.getAllDepartments(-1);
    }

    /**
     * 获取当前登录用户所属员工
     * @return
     */
    @Override
    public Employee getEmployee() {
        Integer eid = UserUtil.getCurrentUser().getEid();
        Employee employee = employeeMapper.getEmployee(eid);
        return employee;
    }

    /**
     * 根据部门获取员工
     * @param id
     * @return
     */
    @Override
    public List<Employee> getEmployeeByDepartment(Integer id) {
        return employeeMapper.getEmployeeByDepartment(id);
    }

    /**
     * 添加新请假申请
     * @param leave
     * @return
     */
    @Override
    public RespBean addLeave(Leave leave) {
        leave.setCreateDate(LocalDate.now());
        if(leaveMapper.insert(leave)==1){
            return RespBean.success("添加成功");
        }
        return RespBean.success("添加失败");
    }

    /**
     * 获取审核者请假申请
     * @param currentPage
     * @param size
     * @param state
     * @param beginDateScope
     * @return
     */
    @Override
    public RespPageBean getAuditorLeave(Integer currentPage, Integer size, String state, LocalDate[] beginDateScope) {
        Integer eid = UserUtil.getCurrentUser().getEid();
        Employee employee = employeeMapper.selectById(eid);
        String workID = employee.getWorkID();
        Page<Task> page=new Page<>(currentPage,size);
        IPage<Task> iPage=leaveMapper.getAuditorLeave(page,state,workID,beginDateScope);
        return new RespPageBean(iPage.getTotal(),iPage.getRecords());
    }

    /**
     * 拒绝请假
     * @param leave
     * @return
     */
    @Override
    public RespBean refuseLeave(Leave leave) {
        leave.setState(Leave.STATE_REFUSE);
        if(leaveMapper.updateById(leave)==1){
            return RespBean.success("操作成功");
        }
        return RespBean.error("操作失败");
    }

    /**
     * 通过请假申请
     * @param leave
     * @return
     */
    @Override
    public RespBean passLeave(Leave leave) {
        leave.setState(Leave.STATE_PASS);
        if(leaveMapper.updateById(leave)==1){
            return RespBean.success("操作成功");
        }
        return RespBean.error("操作失败");
    }
}
