package com.aizhixin.school.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class QrVO {

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

    /**
     * 图片
     */
    @ApiModelProperty(value = "图片")
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

    @ApiModelProperty(value = "打卡人次")
    private Integer total;
}
