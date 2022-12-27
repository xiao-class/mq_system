package com.ruoyi.framework.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.ruoyi.common.enums.DeleteStatus;
import com.ruoyi.common.utils.SecurityUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * @author Class
 */
@Component
public class FillMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("createUserId", SecurityUtils.getUserIdOrDefault(), metaObject);
        this.setFieldValByName("createUserName", SecurityUtils.getUserNameOrDefault(), metaObject);
        this.setFieldValByName("createTime", Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()), metaObject);
        this.setFieldValByName("updateUserId", SecurityUtils.getUserIdOrDefault(), metaObject);
        this.setFieldValByName("updateUserName", SecurityUtils.getUserNameOrDefault(), metaObject);
        this.setFieldValByName("updateTime", Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()), metaObject);
        this.setFieldValByName("isDelete", DeleteStatus.VALID.ordinal(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateUserId", SecurityUtils.getUserIdOrDefault(), metaObject);
        this.setFieldValByName("updateUserName", SecurityUtils.getUserNameOrDefault(), metaObject);
        this.setFieldValByName("updateTime", Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()), metaObject);
    }
}
