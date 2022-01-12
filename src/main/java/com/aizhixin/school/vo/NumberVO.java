package com.aizhixin.school.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class NumberVO {

    @ApiModelProperty(value = "累计管理校内总人数")
    private Integer numberSchool;

    @ApiModelProperty(value = "累计服务出入人次")
    private Integer numberAccess;

    @ApiModelProperty(value = "当前管理教职工人数")
    private Integer numberTeacher;

    @ApiModelProperty(value = "当前管理学生人数")
    private Integer numberStudent;


}
