package com.yuzi.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yuzi.server.pojo.AdminRole;
import com.yuzi.server.pojo.UserRole;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yuzi
 * @since 2023-01-06
 */
public interface UserRoleMapper extends BaseMapper<UserRole> {

    /**
     * 添加操作员角色
     * @param uid
     * @param rids
     * @return
     */
    Integer addUserRoles(@Param("uid") Integer uid, @Param("rids") Integer[] rids);
}
