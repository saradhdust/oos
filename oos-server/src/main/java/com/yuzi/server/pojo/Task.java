package com.yuzi.server.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

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
@TableName("t_task")
@ApiModel(value = "Task对象", description = "")
public class Task {


    public static String TYPE_LOG = "LOG";
    public static String TYPE_PROGRAM = "PROGRAM";
    public static String STATE_AUDIT = "AUDIT";
    public static String STATE_PASS = "PASS";
    public static String STATE_REFUSE = "REFUSE";
    public static String STATE_FINANCE_AUDIT = "FINANCE_AUDIT";

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "申请者工号")
    private String applierWorkID;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd",timezone = "UTC")
    private LocalDate createDate;

    @ApiModelProperty(value = "类型")
    private String type;

    @ApiModelProperty(value = "状态")
    private String state;

    @ApiModelProperty(value = "审核者工号")
    private String auditorWorkID;

    @ApiModelProperty(value = "预计支出")
    private Integer expenditure;

    @ApiModelProperty(value = "预计收入")
    private Integer revenue;

    @ApiModelProperty(value = "审核结果")
    private String auditResult;

    @ApiModelProperty(value = "财务部审批结果")
    @JsonProperty("fAuditResult")
    private String fAuditResult;

    @ApiModelProperty(value = "申请者")
    @TableField(exist = false)
    private Employee applier;
}
