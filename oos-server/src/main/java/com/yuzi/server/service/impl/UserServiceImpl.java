package com.yuzi.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuzi.server.config.security.JwtTokenUtil;
import com.yuzi.server.mapper.*;
import com.yuzi.server.pojo.*;
import com.yuzi.server.service.IUserService;
import com.yuzi.server.utils.AdminUtil;
import com.yuzi.server.utils.StringUtil;
import com.yuzi.server.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author yuzi
 * @since 2023-01-06
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Value("${server.port}")
    private int port;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Value(("${jwt.tokenHead}"))
    private String tokenHead;

    @Override
    public RespBean login(String username, String password) {
        //登录
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (null == userDetails) {
            System.out.println("userDetails is null");
        }
        if (null == userDetails || !password.equals(userDetails.getPassword())) {
            return RespBean.error("用户密码不正确");
        }
        if (!userDetails.isEnabled()) {
            return RespBean.error("账号被禁用，请联系管理员");
        }
        //更新security登录对象
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                = new UsernamePasswordAuthenticationToken(userDetails,
                null,
                userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

//        生成token
        String token = jwtTokenUtil.generateToken(userDetails);
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return RespBean.success("登录成功", tokenMap);
    }

    @Override
    public User getUserByUsername(String username) {
        User user = userMapper.selectOne(new QueryWrapper<User>()
                .eq("username", username)
                .eq("enabled", true));
        return user;
    }

    /**
     * 根据用户id查询角色列表
     *
     * @param uid
     * @return
     */
    @Override
    public List<Role> getRolesByUid(Integer uid) {
        return roleMapper.getRolesByUserId(uid);
    }

    /**
     * 获取所有操作员
     *
     * @param keyword
     * @return
     */
    @Override
    @Transactional
    public List<User> getAllUsers(String keyword) {
        return userMapper.getAllUsers(UserUtil.getCurrentUser().getId(), keyword);

    }

    /**
     * 更新操作员角色
     *
     * @param uid
     * @param rids
     * @return
     */
    @Override
    public RespBean updateUserRole(Integer uid, Integer[] rids) {
        userRoleMapper.delete(new QueryWrapper<UserRole>().eq("uid", uid));
        Integer result = userRoleMapper.addUserRoles(uid, rids);
        if (result == rids.length) {
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败");
    }

    /**
     * 更新密码
     *
     * @param uid
     * @param oldPassword
     * @param password
     * @return
     */
    @Override
    public RespBean updateUserPassword(Integer uid, String oldPassword, String password) {
        User user = userMapper.selectById(uid);
        if (oldPassword.equals(user.getPassword())) {
            user.setPassword(password);
            int result = userMapper.updateById(user);
            if (1 == result) {
                return RespBean.success("更新成功,请重新登录");
            }
        }
        return RespBean.error("密码错误,更新失败");
    }

    /**
     * 更新头像
     *
     * @param file
     * @param id
     * @param authentication
     * @return
     */
    @Override
    public RespBean updateUserFace(MultipartFile file, Integer id, Authentication authentication) {
        String folder = "D:/GRAdesign/Code/img";
        File imgFolder = new File(folder);
        if (!imgFolder.exists()) {
            imgFolder.mkdirs();
        }
        String url = null;
        try {
            //获取原图片格式
            String originalFilename = file.getOriginalFilename();
            String type = originalFilename.substring(originalFilename.lastIndexOf("."));
            //复制图片
            File f = new File(imgFolder, StringUtil.createRandomString() + type);
            file.transferTo(f);
            //生成对应URL
            url = "http://localhost:" + port + "/userFace/" + f.getName();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        User user = userMapper.selectById(id);
        user.setUserFace(url);
        int result = userMapper.updateById(user);
        if (1 == result) {
            User principal = (User) authentication.getPrincipal();
            principal.setUserFace(url);
            SecurityContextHolder.getContext()
                    .setAuthentication(new UsernamePasswordAuthenticationToken(user, null, authentication.getAuthorities()));
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败");
    }

    /**
     * 获取个人中心用户信息
     */
    @Override
    public User getUserinfo() {
        Integer uid = UserUtil.getCurrentUser().getId();
        User currentUser = userMapper.getUser(uid);
        if (currentUser != null) {
            currentUser.setPassword(null);
            Integer eid = currentUser.getEid();
            Employee employee = employeeMapper.getEmployee(eid);
            if (employee != null) {
                currentUser.setEmployee(employee);
                return currentUser;
            }
        }
        return null;
    }

    /**
     * 个人中心更新用户信息
     *
     * @param user
     * @return
     */
    @Override
    @Transactional
    public RespBean updateUserinfo(User user, Authentication authentication) {
        Employee employee = user.getEmployee();
        if (userMapper.updateById(user) == 1 && employeeMapper.updateById(employee) == 1) {
            SecurityContextHolder.getContext()
                    .setAuthentication(new UsernamePasswordAuthenticationToken(user, null, authentication.getAuthorities()));
            return RespBean.success("更新成功,请重新登录");
        }
        return RespBean.success("更新失败");
    }

}
