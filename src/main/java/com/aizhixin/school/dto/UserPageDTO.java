package com.aizhixin.school.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserPageDTO {


    @ApiModelProperty(value = "管理有效期限")
    private String expiry;

    @ApiModelProperty(value = "当前页")
    private Integer pageNumber;

    @ApiModelProperty(value = "显示条数")
    private Integer pageSize;
    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    private String name;

    /**
     * 学号/工号/身份证号
     */
    @ApiModelProperty(value = "学号/工号/身份证号")
    private String card;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "联系电话")
    private String phone;

    @ApiModelProperty(value = "状态 1:在校  0:离校")
    private Integer state;

    @ApiModelProperty(value = "入校时间")
    private LocalDateTime scanCodeTime;

    @ApiModelProperty(value = "类型名称")
    private String typeName;

    @ApiModelProperty(value = "开始时间")
    private LocalDateTime openTime;

    @ApiModelProperty(value = "结束时间")
    private LocalDateTime stopTime;
}
