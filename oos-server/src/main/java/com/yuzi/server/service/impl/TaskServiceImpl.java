package com.yuzi.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuzi.server.mapper.DepartmentMapper;
import com.yuzi.server.mapper.EmployeeMapper;
import com.yuzi.server.mapper.TaskMapper;
import com.yuzi.server.pojo.*;
import com.yuzi.server.service.ITaskService;
import com.yuzi.server.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
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
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task> implements ITaskService {
    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private DepartmentMapper departmentMapper;


    /**
     * 获取申请的任务
     * @return
     */
    @Override
    @Transactional
    public RespPageBean getTasks(Integer currentPage, Integer size, String title, LocalDate[] beginDateScope) {
        Integer eid = UserUtil.getCurrentUser().getEid();
        Employee employee = employeeMapper.selectOne(new QueryWrapper<Employee>().eq("id", eid));
        Page<Task> page = new Page<>(currentPage, size);
        IPage<Task> iPage = taskMapper.getTasksByPage(page, employee.getWorkID(), title, beginDateScope);
        RespPageBean respPageBean = new RespPageBean(iPage.getTotal(), iPage.getRecords());
        return respPageBean;

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
     * 增加新task
     * @param task
     */
    @Override
    public RespBean addTask(Task task) {
        task.setCreateDate(LocalDate.now());
        if(taskMapper.insert(task)==1){
            return RespBean.success("添加成功");
        }
        return RespBean.success("添加失败");
    }

    /**
     * 获取审核者
     * @param auditorWorkID
     * @return
     */
    @Override
    public Employee getAuditor(String auditorWorkID) {
        return employeeMapper.selectOne(new QueryWrapper<Employee>().eq("workID",auditorWorkID));
    }


    /**
     * 获取审核任务
     *
     * @param currentPage
     * @param size
     * @param title
     * @param state
     * @param beginDateScope
     * @return
     */
    @Override
    public RespPageBean getTasksWithApplier(Integer currentPage, Integer size, String title, String state, LocalDate[] beginDateScope) {
        Integer eid = UserUtil.getCurrentUser().getEid();
        Employee employee = employeeMapper.selectById(eid);
        String workID = employee.getWorkID();
        Page<Task> page=new Page<>(currentPage,size);
        IPage<Task> iPage=taskMapper.getTasksWithApplier(page,title,state,workID,beginDateScope);
        return new RespPageBean(iPage.getTotal(),iPage.getRecords());
    }

    /**
     * 驳回申请
     *
     * @param task
     * @return
     */
    @Override
    public RespBean refuseTask(Task task) {
        task.setState(Task.STATE_REFUSE);
        if(taskMapper.updateById(task)==1){
            return RespBean.success("操作成功");
        }
        return RespBean.error("操作失败");
    }


    /**
     * 通过申请
     * @param task
     * @return
     */
    @Override
    public RespBean passTask(Task task) {
        String type = task.getType();
        if(Task.TYPE_LOG.equals(type)){
            task.setState(Task.STATE_PASS);
        }else if(Task.TYPE_PROGRAM.equals(type)){
            if(Task.STATE_AUDIT.equals(task.getState())){
                task.setState(Task.STATE_FINANCE_AUDIT);
            }else if(Task.STATE_FINANCE_AUDIT.equals(task.getState())){
                task.setState(Task.STATE_PASS);
            }
        }
        if(taskMapper.updateById(task)==1){
            return RespBean.success("操作成功");
        }
        return RespBean.error("操作失败");
    }

    /**
     * 获取财务部审核任务
     * @param currentPage
     * @param size
     * @param title
     * @param state
     * @param beginDateScope
     * @return
     */
    @Override
    public RespPageBean getFinTaskWithApplier(Integer currentPage, Integer size, String title, String state, LocalDate[] beginDateScope) {
        Page<Task> page=new Page<>(currentPage,size);
        IPage<Task> iPage=taskMapper.getFinTaskWithApplier(page,title,state,beginDateScope);
        return new RespPageBean(iPage.getTotal(),iPage.getRecords());
    }


}
