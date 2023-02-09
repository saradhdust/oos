package com.yuzi.server.mapper;

import com.yuzi.server.pojo.Role;
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
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 根据用户id查询角色列表
     *
     * @param adminId
     * @return
     */
    List<Role> getRolesByAdminId(Integer adminId);

    /**
     * 根据用户id查询角色列表
     *
     * @param uid
     * @return
     */
    List<Role> getRolesByUserId(Integer uid);
}
