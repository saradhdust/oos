package com.yuzi.server.mapper;

import com.yuzi.server.pojo.AdminRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yuzi
 * @since 2023-01-06
 */
public interface AdminRoleMapper extends BaseMapper<AdminRole> {

    /**
     * 添加操作员角色
     * @param adminId
     * @param rids
     * @return
     */
    Integer addAdminRoles(@Param("adminId") Integer adminId, @Param("rids") Integer[] rids);
}
