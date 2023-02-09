package com.yuzi.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuzi.server.pojo.Task;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author yuzi
 * @since 2023-01-06
 */
public interface TaskMapper extends BaseMapper<Task> {

    /**
     * 获取申请任务（分页）
     * @param page
     * @param workID
     * @param title
     * @param beginDateScope
     * @return
     */
    IPage<Task> getTasksByPage(Page<Task> page,
                               @Param("workID") String workID,
                               @Param("title") String title,
                               LocalDate[] beginDateScope);

    /**
     * 获取审核任务
     * @param page
     * @param title
     * @param state
     * @param workID
     * @param beginDateScope
     * @return
     */
    IPage<Task> getTasksWithApplier(Page<Task> page, @Param("title") String title, @Param("state") String state,@Param("workID") String workID, LocalDate[] beginDateScope);

    /**
     * 获取财务部审核任务
     * @param page
     * @param title
     * @param state
     * @param beginDateScope
     * @return
     */
    IPage<Task> getFinTaskWithApplier(Page<Task> page,@Param("title") String title, @Param("state") String state, LocalDate[] beginDateScope);
}
