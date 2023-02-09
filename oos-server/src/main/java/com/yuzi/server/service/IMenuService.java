package com.yuzi.server.service;

import com.yuzi.server.pojo.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yuzi
 * @since 2023-01-06
 */
public interface IMenuService extends IService<Menu> {

    /**
     * 通过用户id查询菜单列表
     *
     * @return
     */
    List<Menu> getMenuByUserId();

    /**
     * 根据角色获取菜单列表
     * @return
     */
    List<Menu> getMenusByRole();

    /**
     * 获取菜单树
     *
     * @return
     */
    List<Menu> getMenuTree();
}
