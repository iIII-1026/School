package com.aizhixin.school.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Data
public class UserLoginDTO {

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名",required = true)
    @NotBlank(message = "姓名不能为空")
    private String name;

    /**
     * 二维码id
     */
    @ApiModelProperty(value = "二维码id")
    private Integer qrId;

    @ApiModelProperty(value = "登录校验id")
    private Integer openId;

    @ApiModelProperty(value = "扫码时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime curTime;

    @ApiModelProperty(value = "打卡地点")
    private String pip;
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
}
