package com.yuzi.server.controller;

import com.yuzi.server.pojo.Menu;
import com.yuzi.server.service.IConfigService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 星涯
 */
@RestController
@RequestMapping("/system/config")
public class ConfigController {

    @Autowired
    private IConfigService configService;

    @ApiOperation(value = "获取登录用户菜单")
    @GetMapping("/menu")
    public List<Menu> getCurrentUserMenu(){
        return configService.getCurrentUserMenu();
    }

}
