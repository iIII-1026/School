package com.aizhixin.school.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
public class QrDTO {

    /**
     * 二维码id
     */
    @TableId(value = "二维码id",type = IdType.AUTO)
    private Integer id;

    /**
     * 打卡地点
     */
    @ApiModelProperty(value = "打卡地点",required = true)
    @NotBlank(message = "打卡地点不能为空")
    private String pip;

    /**
     * 打卡类型
     */
    @ApiModelProperty(value = "打卡类型",required = true)
    @NotBlank(message = "打卡类型不能为空")
    private String cType;


//    @ApiModelProperty(value = "图片",required = true)
//    @NotBlank(message = "图片不能为空")
//    private String photo;

//    @ApiModelProperty(value = "创建时间",required = true)
//    private Date createTime;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
}
