package com.yuzi.server.config.WebConfigurer;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *WebConfigurer配置
 * @author 星涯
 */
@Configuration
public class MyWebConfigurer implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/userFace/**").addResourceLocations("file:" + "D:/GRAdesign/Code/img/");
    }

}
