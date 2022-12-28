package com.ruoyi.common.core.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: Class
 * @Description: 实体继承基类
 */
@Data
public class AssignBaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //解决swagger获取id精度缺失问题,postman不会有这个问题
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty(value = "主键id")
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    @TableField(value = "CREATE_USER_ID", fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建人id", readOnly = true)
    private Long createUserId;

    @TableField(value = "CREATE_USER_NAME", fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建人名", readOnly = true)
    private String createUserName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间", readOnly = true)
    @TableField(exist = false)
    private Date createDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间", readOnly = true)
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "更新人名", readOnly = true)
    @TableField(value = "UPDATE_USER_ID", fill = FieldFill.INSERT_UPDATE)
    private Long updateUserId;

    @ApiModelProperty(value = "更新人名", readOnly = true)
    @TableField(value = "UPDATE_USER_NAME", fill = FieldFill.INSERT_UPDATE)
    private String updateUserName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "更新时间", readOnly = true)
    @TableField(value = "UPDATE_TIME", updateStrategy = FieldStrategy.NEVER, insertStrategy = FieldStrategy.NEVER)
    private Date updateDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新时间", readOnly = true)
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @TableLogic
    @TableField(value = "IS_DELETE", fill = FieldFill.INSERT)
    @ApiModelProperty(value = "逻辑删除", readOnly = true)
    private Integer isDelete;

    public boolean isNew() {
        return id == null;
    }

    public Date getCreateDate() {
        return this.createTime;
    }
}
