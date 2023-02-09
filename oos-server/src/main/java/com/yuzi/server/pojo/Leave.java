package com.yuzi.server.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

/**
 * <p>
 *
 * </p>
 *
 * @author yuzi
 * @since 2023-01-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_leave")
@ApiModel(value = "Leave对象", description = "")
public class Leave {

    public static String STATE_AUDIT = "AUDIT";
    public static String STATE_PASS = "PASS";
    public static String STATE_REFUSE = "REFUSE";

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "申请者工号")
    private String applierWorkID;

    @ApiModelProperty(value = "审核者工号")
    private String auditorWorkID;

    @ApiModelProperty(value = "理由")
    private String reason;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd",timezone = "UTC")
    private LocalDate createDate;

    @ApiModelProperty(value = "开始时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd",timezone = "UTC")
    private LocalDate beginDate;

    @ApiModelProperty(value = "结束时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd",timezone = "UTC")
    private LocalDate endDate;

    @ApiModelProperty(value = "状态")
    private String state;

    @ApiModelProperty(value = "审核结果")
    private String auditResult;

    @ApiModelProperty(value = "申请者")
    @TableField(exist = false)
    private Employee applier;

    @ApiModelProperty(value = "审核者")
    @TableField(exist = false)
    private Employee auditor;
}
