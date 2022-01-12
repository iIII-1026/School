package com.aizhixin.school.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Data
public class VisitorVO {

    /**
     * 姓名
     */
    @ApiModelProperty(value = "邀请人姓名",required = true)
    @NotBlank(message = "邀请人姓名")
    private String name;

//    /**
//     * 二维码id
//     */
//    @ApiModelProperty(value = "二维码id")
//    private Integer qrId;

//    @ApiModelProperty(value = "二次扫码登录校验id")
//    private Integer openId;

    @ApiModelProperty(value = "登记时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "打卡地点")
    private String pip;
    /**
     * 学号/工号/身份证号
     */
    @ApiModelProperty(value = "邀请人学号/工号",required = true)
    @NotBlank(message = "邀请人学号/工号/身份证号不能为空")
    private String card;

//    @ApiModelProperty(value = "访客身份证号",required = true)
//    @NotBlank(message = "访客身份证号不能为空")
//    private String visitorCard;

    /**
     * 联系电话
     */
    @ApiModelProperty(value = "邀请人联系方式",required = true)
    @NotBlank(message = "邀请人联系方式")
    @Pattern(regexp = "^[1][3,4,5,6,7,8,9][0-9]{9}$", message = "手机号格式有误")
    private String phone;

    @ApiModelProperty(value = "访客联系电话",required = true)
    @NotBlank(message = "访客联系电话")
    @Pattern(regexp = "^[1][3,4,5,6,7,8,9][0-9]{9}$", message = "手机号格式有误")
    private String visitorPhone;

    @ApiModelProperty(value = "访客姓名",required = true)
    @NotBlank(message = "访客姓名")
    private String visitorName;

//    @ApiModelProperty(value = "入校事由")
//    private String info;

    @ApiModelProperty(value = "入校事由")
    private String visitInfo;
}

