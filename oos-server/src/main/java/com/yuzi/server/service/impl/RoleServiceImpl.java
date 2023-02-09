package com.yuzi.server.service.impl;

import com.yuzi.server.pojo.Role;
import com.yuzi.server.mapper.RoleMapper;
import com.yuzi.server.service.IRoleService;
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
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
