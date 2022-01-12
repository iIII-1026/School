package com.aizhixin.school.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MonthVO {

    @ApiModelProperty(value = "总人数")
    private Integer totalNumber;

    @ApiModelProperty(value = "学生数量")
    private Integer studentNumber;

    @ApiModelProperty(value = "教师数量")
    private Integer teacherNumber;

    @ApiModelProperty(value = "访客数量")
    private Integer visitorNumber;
}
