package com.aizhixin.school.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TypeDTO {
    /**
     * 人员类型id
     */
    private Integer id;

    /**
     * 类型名称
     */
    @ApiModelProperty(value = "类型名称")
//    @NotBlank(message = "类型名称不能为空")
    private String typeName;


    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
}
