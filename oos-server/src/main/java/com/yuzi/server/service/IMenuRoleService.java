package com.yuzi.server.service;

import com.yuzi.server.pojo.MenuRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yuzi.server.pojo.RespBean;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yuzi
 * @since 2023-01-06
 */
public interface IMenuRoleService extends IService<MenuRole> {

    /**
     * 更新角色菜单
     * @param rid
     * @param mids
     * @return
     */
    RespBean updateMenuRole(Integer rid, Integer[] mids);
}
