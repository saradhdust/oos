package com.yuzi.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yuzi.server.mapper.AdminMapper;
import com.yuzi.server.mapper.AdminRoleMapper;
import com.yuzi.server.mapper.MenuMapper;
import com.yuzi.server.mapper.UserMapper;
import com.yuzi.server.pojo.Admin;
import com.yuzi.server.pojo.AdminRole;
import com.yuzi.server.pojo.Menu;
import com.yuzi.server.pojo.User;
import com.yuzi.server.service.IConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author yuzi
 * @since 2023-01-06
 */
@Service
public class ConfigService implements IConfigService {
    @Autowired
    AdminMapper adminMapper;

    @Autowired
    MenuMapper menuMapper;

    @Autowired
    UserMapper userMapper;

    @Override
    public List<Menu> getCurrentUserMenu() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("username", username));
        List<Menu> menuTree = menuMapper.getMenuTreeByUserId(user.getId());
        return menuTree;
    }
}
