package com.yuzi.server.Exception;

import com.yuzi.server.pojo.RespBean;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

/**
 * 全局异常处理
 *
 * @author 星涯
 */
@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler
    public RespBean MySQLException(SQLException e) {
        System.out.println("Error：" +e);
        return RespBean.error("操作被禁止，操作失败！");
    }
}
