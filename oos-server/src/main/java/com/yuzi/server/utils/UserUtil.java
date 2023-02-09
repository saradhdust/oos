package com.yuzi.server.utils;

import com.yuzi.server.pojo.Admin;
import com.yuzi.server.pojo.User;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 用户工具类
 *
 * @author 星涯
 */
public class UserUtil {
    /**
     * 获取当前用户
     * @return
     */
    public static User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
