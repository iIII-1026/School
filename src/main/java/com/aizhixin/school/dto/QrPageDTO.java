package com.aizhixin.school.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class QrPageDTO {
    /**
     * 二维码id
     */
    @ApiModelProperty(value = "二维码id")
    private Integer id;

    /**
     * 打卡地点
     */
    @ApiModelProperty(value = "打卡地点")
    private String pip;

    /**
     * 打卡类型
     */
    @ApiModelProperty(value = "打卡类型")
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

    @ApiModelProperty(value = "开始时间")
    private LocalDateTime openTime;

    @ApiModelProperty(value = "结束时间")
    private LocalDateTime stopTime;

    @ApiModelProperty(value = "当前页",required = true)
    @NotNull(message = "当前页不能为空")
    private Integer pageNumber;

    @ApiModelProperty(value = "显示条数",required = true)
    @NotNull(message = "显示条数不能为空")
    private Integer pageSize;
}
