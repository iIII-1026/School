package com.aizhixin.school.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class TypeSelectDTO {

    @ApiModelProperty(value = "开始时间",required = true)
    @JsonFormat( pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime startTime;

    @ApiModelProperty(value = "结束时间",required = true)
    @JsonFormat( pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime stopTime;

    @ApiModelProperty(value = "当前页",required = true)
    @NotNull(message = "当前页不能为空")
    private Integer pageNumber;

    @ApiModelProperty(value = "显示条数",required = true)
    @NotNull(message = "显示条数不能为空")
    private Integer pageSize;
}
