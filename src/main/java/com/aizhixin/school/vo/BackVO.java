package com.aizhixin.school.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BackVO {

    private Integer code;

    @ApiModelProperty(value = "响应消息")
    private String msg;

    @ApiModelProperty(value = "公共类型的数据")
    private Object data;
}
