package com.aizhixin.school.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class MonthDTO {

    @ApiModelProperty(value = "出入口id",required = true)
    @NotNull(message = "出入口id不能为空")
    private Integer gatewayId;

    @ApiModelProperty(value = "当前页",required = true)
    @NotNull(message = "当前页不能为空")
    private Integer pageNumber;

    @ApiModelProperty(value = "显示条数",required = true)
    @NotNull(message = "显示条数不能为空")
    private Integer pageSize;

}
