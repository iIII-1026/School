package com.aizhixin.school.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import com.aizhixin.school.domain.Qr;
import com.aizhixin.school.domain.Type;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@ExcelTarget("access")
public class AccessVO {

    /**
     *
     */
    @Excel(name = "编号")
    private Integer id;

//    /**
//     * 人员信息id
//     */
//    @ApiModelProperty(value = "人员信息id",required = true)
//    private Integer userId;
//
//    /**
//     * 二维码id
//     */
//    @ApiModelProperty(value = "二维码id",required = true)
//    private Integer qrId;
//
//    /**
//     * 扫码时间
//     */
//    @ApiModelProperty(value = "扫码时间",required = true)
//    private Date currentTime;
//
//
//    private List<Qr> qrs;
//
//    private List<Type> types;
//    @ApiModelProperty(value = "当前页")
//    private Integer pageNumber;
//
//    @ApiModelProperty(value = "显示条数")
//    private Integer pageSize;

    /**
     * 打卡地点
     */
    @ApiModelProperty(value = "打卡地点")
    @Excel(name = "打卡地点")
    private String pip;

    /**
     * 打卡类型
     */
    @ApiModelProperty(value = "打卡类型")
    @Excel(name = "打卡类型")
    private String cType;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    @Excel(name = "姓名")
    private String name;

    /**
     * 学号/工号/身份证号
     */
    @ApiModelProperty(value = "学号/工号/身份证号")
    @Excel(name = "学号/工号/身份证号")
    private String card;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @Excel(name = "备注")
    private String remark;

    @ApiModelProperty(value = "联系电话")
    @Excel(name = "联系电话")
    private String phone;

    @ApiModelProperty(value = "扫码时间")
    @Excel(name = "扫码时间",width = 35.0, format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime scanCodeTime;

    @ApiModelProperty(value = "类型名称")
    @Excel(name = "类型名称")
    private String typeName;
}
