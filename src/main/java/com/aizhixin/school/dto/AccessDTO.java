package com.aizhixin.school.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class AccessDTO {
//    /**
//     * 出入id
//     */
//    @TableId(value = "id",type = IdType.AUTO)
//    private Integer id;
//
//    /**
//     * 人员信息id
//     */
//    @ApiModelProperty(value = "人员信息id",required = true)
//    @NotNull(message = "人员信息id不能为空")
//    private Integer userId;
//
//    /**
//     * 二维码id
//     */
//    @ApiModelProperty(value = "二维码id",required = true)
//    @NotNull(message = "二维码id不能为空")
//    private Integer qrId;
//

    @ApiModelProperty(value = "当前页",required = true)
    private Integer pageNumber;

    @ApiModelProperty(value = "显示条数",required = true)
    private Integer pageSize;

    @ApiModelProperty(value = "开始时间")
    private LocalDateTime openTime;

    @ApiModelProperty(value = "结束时间")
    private LocalDateTime stopTime;

    /**
     * 打卡地点
     */
    @ApiModelProperty(value = "打卡地点")
    private String pip;

    /**
     * 打卡类型
     */
    @ApiModelProperty(value = "打卡类型")
    private String cType;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    private String name;

    /**
     * 学号/工号/身份证号
     */
    @ApiModelProperty(value = "学号/工号/身份证号")
    private String card;

    @ApiModelProperty(value = "类型名称")
    private String typeName;
}
