package com.yuzi.server.controller;


import com.yuzi.server.pojo.Position;
import com.yuzi.server.pojo.RespBean;
import com.yuzi.server.service.IPositionService;
import io.swagger.annotations.ApiModelProperty;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author yuzi
 * @since 2023-01-06
 */
@RestController
@RequestMapping("/system/basic/pos")
public class PositionController {
    @Autowired
    private IPositionService positionService;

    @ApiModelProperty(value = "获取所有职位信息")
    @GetMapping("/")
    public List<Position> getAllPositions() {
        return positionService.list();
    }

    @ApiModelProperty(value = "添加职位信息")
    @PostMapping("/")
    public RespBean addPosition(@RequestBody Position position) {
        position.setCreateDate(LocalDateTime.now());
        if (positionService.save(position)) {
            return RespBean.success("添加成功！");
        } else {
            return RespBean.error("添加失败");
        }
    }

    @ApiModelProperty(value = "更新职位信息")
    @PutMapping("/")
    public RespBean updatePositions(@RequestBody Position position) {
        if (positionService.updateById(position)) {
            return RespBean.success("更新成功");
        } else {
            return RespBean.error("更新失败");
        }
    }

    @ApiModelProperty(value = "删除职位信息")
    @DeleteMapping("/{id}")
    public RespBean deletePosition(@PathVariable Integer id) {
        if (positionService.removeById(id)) {
            return RespBean.success("删除成功");
        } else {
            return RespBean.error("删除失败!");
        }
    }

    @ApiModelProperty(value = "批量删除职位信息")
    @DeleteMapping("/")
    public RespBean deletePositionByIds(Integer[] ids) {
        if (positionService.removeByIds(Arrays.asList(ids))) {
            return RespBean.success("删除成功");
        } else {
            return RespBean.error("删除失败!");
        }
    }
}
