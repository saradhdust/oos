package com.yuzi.server.utils;

import com.yuzi.server.pojo.Admin;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 操作员工具类
 *
 * @author 星涯
 */
public class AdminUtil {
    /**
     * 获取当前操作员
     * @return
     */
    public static Admin getCurrentAdmin() {
        return (Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
