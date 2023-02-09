package com.yuzi.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yuzi.server.pojo.Admin;
import com.yuzi.server.pojo.RespBean;
import com.yuzi.server.pojo.Role;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author yuzi
 * @since 2023-01-06
 */
public interface IAdminService extends IService<Admin> {

    /**
     * 登录之后返回token
     *
     * @param username
     * @param password
     * @return
     */
    RespBean login(String username, String password);

    /**
     * 根据用户名获取用户
     *
     * @param username
     * @return
     */
    Admin getAdminByUsername(String username);

    /**
     * 根据用户id查询角色列表
     *
     * @param adminId
     * @return
     */
    List<Role> getRolesByAdminId(Integer adminId);

    /**
     * 获取所有操作员
     *
     * @param keyword
     * @return
     */
    List<Admin> getAllAdmins(String keyword);

    /**
     * 更新操作员角色
     *
     * @param adminId
     * @param rids
     * @return
     */
    RespBean updateAdminRole(Integer adminId, Integer[] rids);

    /**
     * 更新密码
     *
     * @param adminId
     * @param oldPassword
     * @param password
     * @return
     */
    RespBean updateAdminPassword(Integer adminId, String oldPassword, String password);

    /**
     * 更新头像
     *
     * @param file
     * @param id
     * @param authentication
     * @return
     */
    RespBean updateAdminUserFace(MultipartFile file, Integer id, Authentication authentication);
}
