package com.aizhixin.school.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 
 * @TableName t_access
 */
@TableName(value ="t_access")
@Data
public class Access implements Serializable {
    /**
     * 
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /**
     * 人员信息id
     */
    @ApiModelProperty(value = "人员信息id",required = true)
    private Integer userId;

    /**
     * 二维码id
     */
    @ApiModelProperty(value = "二维码id",required = true)
    private Integer qrId;

    /**
     * 扫码时间
     */
    @ApiModelProperty(value = "扫码时间",required = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime scanCodeTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}