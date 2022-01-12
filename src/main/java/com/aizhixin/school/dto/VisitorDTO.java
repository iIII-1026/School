package com.aizhixin.school.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Data
public class VisitorDTO {
//
//    @ApiModelProperty(value = "人员信息id")
//    private Integer id;

    /**
     * 人员类型id
     */
    @ApiModelProperty(value = "人员类型id")
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
    @ApiModelProperty(value = "其他信息（访客）")
    private String info;

    /**
     * 有效期限
     */
    @ApiModelProperty(value = "有效期限")
    private String expiry;

    /**
     * 状态 1:在校  0:离校
     */
    @ApiModelProperty(value = "状态 1:在校  0:离校")
    private Integer state;

    /**
     * 入校时间
     */
//    @ApiModelProperty(value = "入校时间")
//    @JsonFormat( pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
//    private LocalDateTime createTime;


    /**
     * 二维码id
     */
    @ApiModelProperty(value = "二维码id")
    private Integer qrId;

//    @ApiModelProperty(value = "二次扫码登录校验id")
//    private Integer openId;

    @ApiModelProperty(value = "登记时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "打卡地点")
    private String pip;

    @ApiModelProperty(value = "访客身份证号",required = true)
    @NotBlank(message = "访客身份证号")
    private String visitorCard;


    @ApiModelProperty(value = "邀请人联系电话",required = true)
    @NotBlank(message = "邀请人联系电话")
    @Pattern(regexp = "^[1][3,4,5,6,7,8,9][0-9]{9}$", message = "手机号格式有误")
    private String visitorPhone;

    @ApiModelProperty(value = "邀请人姓名",required = true)
    @NotBlank(message = "邀请人姓名")
    private String visitorName;

    @ApiModelProperty(value = "访客事由")
    private String visitInfo;
}
