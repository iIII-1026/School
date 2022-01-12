package com.aizhixin.school.domain;

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
 * @TableName t_qr
 */
@TableName(value ="t_qr")
@Data
public class Qr implements Serializable {
    /**
     * 二维码id
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /**
     * 打卡地点
     */
    @ApiModelProperty(value = "打卡地点",required = true)
    private String pip;

    /**
     * 打卡类型
     */
    @ApiModelProperty(value = "打卡类型",required = true)
    private String cType;

    /**
     * 图片
     */
    @ApiModelProperty(value = "图片",required = true)
    private String photo;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间",required = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}