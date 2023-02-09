package com.yuzi.server.service.impl;

import com.yuzi.server.pojo.Admin;
import com.yuzi.server.pojo.Menu;
import com.yuzi.server.mapper.MenuMapper;
import com.yuzi.server.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuzi.server.utils.AdminUtil;
import com.yuzi.server.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
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
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {
    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 根据用户id查询菜单列表
     *
     * @return
     */
    @Override
    public List<Menu> getMenuByUserId() {
//        加入redis后写法
//        Integer uid = UserUtil.getCurrentUser().getId();
//        从redis获取菜单数据
//        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
//        List<Menu> menus = (List<Menu>) valueOperations.get("menus_" + uid);
//        若redis中没有，则从数据库中获取,并放到redis中
//        if (CollectionUtils.isEmpty(menus)) {
//            menus = menuMapper.getMenuByUserId(uid);
//            valueOperations.set("menus_" + uid, menus);
//        }
//        return menus;
//        原始写法
        return menuMapper.getMenuByUserId(UserUtil.getCurrentUser().getId());
    }


    /**
     * 根据角色获取菜单列表
     *
     * @return
     */
    @Override
    public List<Menu> getMenusByRole() {
        return menuMapper.getMenusByRole();

    }


    /**
     * 获取菜单树
     *
     * @return
     */
    @Override
    public List<Menu> getMenuTree() {
        return menuMapper.getMenuTree();
    }
}
