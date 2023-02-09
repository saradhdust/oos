package com.yuzi.server.mapper;

import com.yuzi.server.pojo.MenuRole;
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
public interface MenuRoleMapper extends BaseMapper<MenuRole> {

    /**
     * 插入角色菜单
     * @param rid
     * @param mids
     * @return
     */
    Integer insertRecord(@Param("rid") Integer rid, @Param("mids") Integer[] mids);
}
