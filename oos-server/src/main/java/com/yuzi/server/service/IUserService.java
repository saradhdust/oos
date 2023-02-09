package com.yuzi.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yuzi.server.pojo.RespBean;
import com.yuzi.server.pojo.Role;
import com.yuzi.server.pojo.User;
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
public interface IUserService extends IService<User> {

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
    User getUserByUsername(String username);

    /**
     * 根据用户id查询角色列表
     *
     * @param uid
     * @return
     */
    List<Role> getRolesByUid(Integer uid);

    /**
     * 获取所有操作员
     *
     * @param keyword
     * @return
     */
    List<User> getAllUsers(String keyword);

    /**
     * 更新操作员角色
     *
     * @param uid
     * @param rids
     * @return
     */
    RespBean updateUserRole(Integer uid, Integer[] rids);

    /**
     * 更新密码
     *
     * @param uid
     * @param oldPassword
     * @param password
     * @return
     */
    RespBean updateUserPassword(Integer uid, String oldPassword, String password);

    /**
     * 更新头像
     *
     * @param file
     * @param id
     * @param authentication
     * @return
     */
    RespBean updateUserFace(MultipartFile file, Integer id, Authentication authentication);

    /**
     * 获取个人中心用户信息
     *
     * @return
     */
    User getUserinfo();


    /**
     * 个人中心更新用户信息
     * @param user
     * @param authentication
     * @return
     */
    RespBean updateUserinfo(User user,Authentication authentication);
}
