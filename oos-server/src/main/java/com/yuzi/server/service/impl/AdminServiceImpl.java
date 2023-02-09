package com.yuzi.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yuzi.server.config.security.JwtTokenUtil;
import com.yuzi.server.mapper.AdminRoleMapper;
import com.yuzi.server.mapper.RoleMapper;
import com.yuzi.server.pojo.Admin;
import com.yuzi.server.mapper.AdminMapper;
import com.yuzi.server.pojo.AdminRole;
import com.yuzi.server.pojo.RespBean;
import com.yuzi.server.pojo.Role;
import com.yuzi.server.service.IAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuzi.server.utils.AdminUtil;
import com.yuzi.server.utils.StringUtil;
import jdk.nashorn.internal.ir.ReturnNode;
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
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {
    @Value("${server.port}")
    private int port;

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private AdminRoleMapper adminRoleMapper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

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
    public Admin getAdminByUsername(String username) {
        Admin admin = adminMapper.selectOne(new QueryWrapper<Admin>()
                .eq("username", username)
                .eq("enabled", true));
        return admin;
    }

    /**
     * 根据用户id查询角色列表
     *
     * @param adminId
     * @return
     */
    @Override
    public List<Role> getRolesByAdminId(Integer adminId) {
        return roleMapper.getRolesByAdminId(adminId);
    }


    /**
     * 获取所有操作员
     *
     * @param keywords
     * @return
     */
    @Override
    public List<Admin> getAllAdmins(String keywords) {
        return adminMapper.getAllAdmins(AdminUtil.getCurrentAdmin().getId(), keywords);

    }

    /**
     * 更新操作员角色
     *
     * @param adminId
     * @param rids
     * @return
     */
    @Override
    @Transactional
    public RespBean updateAdminRole(Integer adminId, Integer[] rids) {
        adminRoleMapper.delete(new QueryWrapper<AdminRole>().eq("adminId", adminId));
        Integer result = adminRoleMapper.addAdminRoles(adminId, rids);
        if (result == rids.length) {
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败");
    }

    /**
     * 更新密码
     *
     * @param adminId
     * @param oldPassword
     * @param password
     * @return
     */
    @Override
    public RespBean updateAdminPassword(Integer adminId, String oldPassword, String password) {
        Admin admin = adminMapper.selectById(adminId);
        if (oldPassword.equals(admin.getPassword())) {
            admin.setPassword(password);
            int result = adminMapper.updateById(admin);
            if (1 == result) {
                return RespBean.success("更新成功");
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
    public RespBean updateAdminUserFace(MultipartFile file, Integer id, Authentication authentication) {
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
        Admin admin = adminMapper.selectById(id);
        admin.setUserFace(url);
        int result = adminMapper.updateById(admin);
        if (1 == result) {
            Admin principal = (Admin) authentication.getPrincipal();
            principal.setUserFace(url);
            SecurityContextHolder.getContext()
                    .setAuthentication(new UsernamePasswordAuthenticationToken(admin, null, authentication.getAuthorities()));
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败");

    }

}
