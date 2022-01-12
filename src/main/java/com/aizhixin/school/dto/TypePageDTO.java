package com.aizhixin.school.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class TypePageDTO {
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
