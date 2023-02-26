package com.xxx.common;

import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;

import java.time.LocalDateTime;

/**
 * 设置自动填充的值
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler{
    @Override
    public void insertFill(MetaObject metaObject) {
        metaObject.setValue("createTime", LocalDateTime.now());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
//        metaObject.setValue("updateTime",LocalDateTime.now());
    }
}
