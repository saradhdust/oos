package com.yuzi.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuzi.server.pojo.Admin;
import com.yuzi.server.pojo.Employee;
import com.yuzi.server.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author yuzi
 * @since 2023-01-06
 */
public interface UserMapper extends BaseMapper<User> {


    /**
     * 获取所有用户
     *
     * @param id
     * @param keywords
     * @return
     */
    List<User> getAllUsers(@Param("id") Integer id, @Param("keywords") String keywords);

    /**
     * 获取一个用户
     *
     * @param uid
     * @return
     */
    User getUser(Integer uid);


    /**
     * 获取携带员工信息的用户账号列表
     * @param offset
     * @param size
     * @param workID
     * @param beginDateScope
     * @return
     */
    List<User> getUserListWithEmp(@Param("offset") Integer offset,
                                  @Param("size") Integer size,
                                  @Param("workID") String workID,
                                  @Param("beginDateScope") LocalDate[] beginDateScope);
}
