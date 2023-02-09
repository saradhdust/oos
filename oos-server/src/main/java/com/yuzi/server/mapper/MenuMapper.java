package com.yuzi.server.mapper;

import com.yuzi.server.pojo.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author yuzi
 * @since 2023-01-06
 */
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 通过用户id查询菜单列表
     *
     * @param id
     * @return
     */
    List<Menu> getMenuByUserId(Integer id);

    /**
     * 根据角色获取菜单列表
     *
     * @return
     */
    List<Menu> getMenusByRole();

    /**
     * 获取菜单树
     *
     * @return
     */
    List<Menu> getMenuTree();

    /**
     * 根据用户id获取菜单树
     * @param id
     * @return
     */
    List<Menu> getMenuTreeByUserId(Integer id);
}
