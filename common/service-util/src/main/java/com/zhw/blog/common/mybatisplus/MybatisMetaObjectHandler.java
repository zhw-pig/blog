package com.zhw.blog.common.mybatisplus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author zhanghuaiwei
 * @date 2024/11/19 14:44
 */
// MybatisMetaObjectHandler搭配@TableField  实现数据库字段填充
// 保存或更新数据时，前端通常不会传入`isDeleted`、`createTime`、`updateTime`这三个字段，
// 因此我们需要手动赋值。但是数据库中几乎每张表都有上述字段，所以手动去赋值就显得有些繁琐。为简化上述操作
// 使用mybatis-plus的自动填充功能，所谓自动填充，就是通过统一配置，在插入或更新数据时，自动为某些字段赋值
@Component
public class MybatisMetaObjectHandler implements MetaObjectHandler {
    @Override
    // @TableField(value = "create_time", fill = FieldFill.INSERT)
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "createTime", Date.class, new Date());
    }


    @Override
    // @TableField(value = "update_time", fill = FieldFill.UPDATE)
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "updateTime", Date.class, new Date());
    }
}
