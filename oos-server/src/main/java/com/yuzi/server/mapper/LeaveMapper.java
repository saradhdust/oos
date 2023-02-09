package com.yuzi.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuzi.server.pojo.Leave;
import com.yuzi.server.pojo.Task;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yuzi
 * @since 2023-01-06
 */
public interface LeaveMapper extends BaseMapper<Leave> {

    /**
     * 获取请假列表
     * @param page
     * @param workID
     * @param state
     * @param beginDateScope
     * @return
     */
    IPage<Leave> getLeaveList(Page<Leave> page, @Param("workID") String workID, @Param("state") String state, LocalDate[] beginDateScope);


    /**
     * 获取审核者请假申请
     * @param page
     * @param state
     * @param workID
     * @param beginDateScope
     * @return
     */
    IPage<Task> getAuditorLeave(Page<Task> page, @Param("state") String state, @Param("workID") String workID, LocalDate... beginDateScope);
}
