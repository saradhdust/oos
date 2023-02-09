package com.yuzi.service.impl;

import com.yuzi.pojo.Employee;
import com.yuzi.mapper.EmployeeMapper;
import com.yuzi.service.IEmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yuzi
 * @since 2023-01-06
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {

}
