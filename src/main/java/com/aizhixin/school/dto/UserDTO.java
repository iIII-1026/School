package com.aizhixin.school.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;


@Data
public class UserDTO {

    @ApiModelProperty(value = "人员信息id",required = true)
    private Integer id;

    /**
     * 人员类型id
     */
    @ApiModelProperty(value = "人员类型id",required = true)
    @NotNull(message = "人员类型id不能为空")
    private Integer typeId;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名",required = true)
    @NotBlank(message = "姓名不能为空")
    private String name;

    /**
     * 学号/工号/身份证号
     */
    @ApiModelProperty(value = "学号/工号/身份证号",required = true)
    @NotBlank(message = "学号/工号/身份证号不能为空")
    private String card;

    /**
     * 联系电话
     */
    @ApiModelProperty(value = "联系电话",required = true)
    @NotBlank(message = "联系电话不能为空")
    @Pattern(regexp = "^[1][3,4,5,6,7,8,9][0-9]{9}$", message = "手机号格式有误")
    private String phone;

    /**
     * 其他信息（访客）
     */
    @ApiModelProperty(value = "其他信息（访客）",required = true)
    @NotBlank(message = "其他信息（访客）不能为空")
    private String info;

    /**
     * 有效期限
     */
    @ApiModelProperty(value = "有效期限",required = true)
    @NotBlank(message = "有效期限不能为空")
    private String expiry;

    /**
     * 状态 1:在校  0:离校
     */
    @ApiModelProperty(value = "状态 1:在校  0:过期",required = true)
    @NotNull(message = "状态不能为空")
    private Integer state;

    /**
     * 入校时间
     */
    @ApiModelProperty(value = "入校时间")
    @JsonFormat( pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime createTime;

    //    @ApiModelProperty(value = "修改时间")
//    private LocalDateTime modifyTime;
}
