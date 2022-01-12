package com.aizhixin.school.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TypeSelectVO {

    @ApiModelProperty(value = "类型名称")
    private String typeName;

    @ApiModelProperty(value = "入口1")
    private String entrance1;

    @ApiModelProperty(value = "出口1")
    private String export1;

    @ApiModelProperty(value = "出口2")
    private String export2;

    @ApiModelProperty(value = "入口1")
    private String entrance2;

    @ApiModelProperty(value = "出口3")
    private String export3;

}
