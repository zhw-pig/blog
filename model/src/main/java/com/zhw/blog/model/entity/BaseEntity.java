package com.zhw.blog.model.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhanghuaiwei
 * @date 2024/12/26 9:30
 * @description 所有实体类都需要实现序列化接口，所以所有实体类继承BaseEntity即可
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
// @JsonInclude(JsonInclude.Include.NON_NULL)  // 不会反序列化给前端null值
public class BaseEntity implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 数据库表主键id
     */
    @Schema(description = "主键id")
    @TableId("id")
    private String id;
    /**
     * 数据库表记录创建时间
     */
    @Schema(description = "创建时间")
    @DateTimeFormat(pattern="yyyy-MM-dd") // 将前端传入的日期字符串解析为Date对象
    @JsonFormat(pattern="yyyy-MM-dd") // 格式化返回给前端的日期
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;
    /**
     * 数据库表记录修改时间
     */
    @Schema(description = "修改时间")
    @DateTimeFormat(pattern="yyyy-MM-dd") // 将前端传入的日期字符串解析为Date对象
    @JsonFormat(pattern="yyyy-MM-dd") // 格式化返回给前端的日期
    @TableField(value = "last_modified_time", fill = FieldFill.UPDATE)
    private Date lastModifiedTime;

    /**
     * 记录创建者ID
     */
    @TableField("create_by")
    private String createBy;

    /**
     * 最后修改人ID
     */
    @TableField("last_modified_by")
    private String lastModifiedBy;

    /**
     * 创建人名称
     */
    @TableField("create_name")
    private String createName;

    /**
     * 最后修改人名称
     */
    @TableField("last_modified_name")
    private String lastModifiedName;

    /**
     * 数据库表记录软删除标记
     */
    @Schema(description = "删除标记")
    @TableField("is_delete")
    @JsonIgnore
    @TableLogic
    private Byte isDelete;
}
