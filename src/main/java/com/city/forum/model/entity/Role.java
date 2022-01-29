package com.city.forum.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * cityforum
 * 权限角色实体类
 *
 * @author : chenDW
 * @date : 2021-09-12 16:19
 **/
@Data
@Accessors(chain = true)
@TableName("role")
public class Role {

    /**
     * 主键角色id
     */
    @TableId
    private Integer roleId;
    /**
     * 角色名称
     */
    private String roleName;
}
