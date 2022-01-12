package com.aizhixin.school.domain;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 
 * @TableName t_user
 */
@TableName(value ="t_user")
@Data
@ExcelTarget("t_user")
public class User implements Serializable {
    /**
     * 人员信息id
     */
    @Excel(name = "编号")
    @TableId(value = "id",type = IdType.AUTO)
    @ApiModelProperty(value = "人员信息id")
    private Integer id;

    /**
     * 二次扫码登录校验id
     */
    @Excel(name = "二次扫码登录校验id")
    @ApiModelProperty(value = "二次扫码登录校验id")
    private Integer openId;

    /**
     * 人员类型id
     */
    @Excel(name = "人员类型id")
    @ApiModelProperty(value = "人员类型id")
    private Integer typeId;



    /**
     * 姓名
     */
    @Excel(name = "姓名")
    @ApiModelProperty(value = "姓名")
    private String name;

    /**
     * 学号/工号/身份证号
     */
    @Excel(name = "学号/工号/身份证号")
    @ApiModelProperty(value = "学号/工号/身份证号")
    private String card;

    /**
     * 联系电话
     */
    @Excel(name = "联系电话")
    @ApiModelProperty(value = "联系电话")
    private String phone;

    /**
     * 其他信息（访客）
     */
    @Excel(name = "其他信息（访客）")
    @ApiModelProperty(value = "其他信息（访客）")
    private String info;

    /**
     * 有效期限
     */
    @Excel(name = "有效期限")
    @ApiModelProperty(value = "有效期限")
    private String expiry;

    /**
     * 状态 1:在校  0:离校
     */
    @Excel(name = "状态",replace = {" 在校_1  离校_0"})
    @ApiModelProperty(value = "状态 1:在校  0:离校")
    private Integer state;

    /**
     * 入校时间
     */
    @Excel(name = "入校时间",width = 35.0,format = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "入校时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @Excel(name = "修改时间")
    @ApiModelProperty(value = "修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime modifyTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}