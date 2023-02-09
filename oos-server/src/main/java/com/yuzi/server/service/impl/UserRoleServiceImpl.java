package com.yuzi.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuzi.server.mapper.AdminRoleMapper;
import com.yuzi.server.mapper.UserRoleMapper;
import com.yuzi.server.pojo.AdminRole;
import com.yuzi.server.pojo.UserRole;
import com.yuzi.server.service.IAdminRoleService;
import com.yuzi.server.service.IUserRoleService;
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
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

}
