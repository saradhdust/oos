package com.yuzi.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuzi.server.mapper.UserMapper;
import com.yuzi.server.pojo.Employee;
import com.yuzi.server.mapper.EmployeeMapper;
import com.yuzi.server.pojo.RespBean;
import com.yuzi.server.pojo.RespPageBean;
import com.yuzi.server.pojo.User;
import com.yuzi.server.service.IEmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuzi.server.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author yuzi
 * @since 2023-01-06
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private UserMapper userMapper;

    /**
     * 获取所有员工(分页)
     *
     * @param currentPage
     * @param size
     * @param employee
     * @param beginDateScope
     * @return
     */
    @Override
    public RespPageBean getEmployeeByPage(Integer currentPage,
                                          Integer size,
                                          Employee employee,
                                          LocalDate[] beginDateScope) {
//        开启分页
        Page<Employee> page = new Page<Employee>(currentPage, size);
        IPage<Employee> employeeByPage = employeeMapper.getEmployeeByPage(page, employee, beginDateScope);
        RespPageBean respPageBean = new RespPageBean(employeeByPage.getTotal(), employeeByPage.getRecords());
        return respPageBean;
    }

    /**
     * 获取新工号
     *
     * @return
     */
    @Override
    public RespBean getMaxWorkID() {
        List<Map<String, Object>> maps = employeeMapper.selectMaps(new QueryWrapper<Employee>().select("max(workID)"));
        Integer workID = Integer.parseInt(maps.get(0).get("max(workID)").toString());
        return RespBean.success(null, String.format("%08d", workID + 1));
    }

    /**
     * 添加新员工
     *
     * @param employee
     * @return
     */
    @Override
    public RespBean addEmployee(Employee employee) {
//        处理合同期限，保留两位小鼠
//        LocalDate beginContract = employee.getBeginContract();
//        LocalDate endContract = employee.getEndContract();
//        long days = beginContract.until(endContract, ChronoUnit.DAYS);
//        DecimalFormat decimalFormat = new DecimalFormat("##.00");
//        employee.setContractTerm(Double.parseDouble(decimalFormat.format(days / 365.00)));

//        插入数据
        if (employeeMapper.insert(employee) == 1) {
            return RespBean.success("添加成功");
        }
        return RespBean.error("添加失败");
    }


    /**
     * 更新员工
     *
     * @param employee
     * @return
     */
    @Override
    public RespBean updateEmployee(Employee employee) {
//        处理合同期限，保留两位小鼠
//        LocalDate beginContract = employee.getBeginContract();
//        LocalDate endContract = employee.getEndContract();
//        long days = beginContract.until(endContract, ChronoUnit.DAYS);
//        DecimalFormat decimalFormat = new DecimalFormat("##.00");
//        employee.setContractTerm(Double.parseDouble(decimalFormat.format(days / 365.00)));

//        更新数据
        if (employeeMapper.updateById(employee) == 1) {
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败");

    }

    /**
     * 获取所有工资账套
     *
     * @param currentPage
     * @param size
     */
    @Override
    public RespPageBean getEmployeeWithSalary(Integer currentPage, Integer size) {
//        开启分页
        Page<Employee> page = new Page<>(currentPage, size);

        IPage<Employee> employeeIPage = employeeMapper.getEmployeeWithSalary(page);
        RespPageBean respPageBean = new RespPageBean(employeeIPage.getTotal(), employeeIPage.getRecords());
        return respPageBean;
    }

    /**
     * @param id
     * @return
     */
    @Override
    @Transactional
    public RespBean deleteEmployee(Integer id) {
        //查询员工的账号
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("eid", id));
//        若员工账号存在，则删除该账号
        if(user!=null){
            if(userMapper.deleteById(user.getId())==0){
                return RespBean.error("删除失败");
            }
        }
        if(employeeMapper.deleteById(id)==0){
            return RespBean.error("删除失败");
        }
        return RespBean.success("删除成功");
    }
}
