package com.yuzi.server.service;

import com.yuzi.server.pojo.Menu;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yuzi
 * @since 2023-01-06
 */
public interface IConfigService {

    /**获取当前登录用户菜单
     * @return
     */
    List<Menu> getCurrentUserMenu();
}
