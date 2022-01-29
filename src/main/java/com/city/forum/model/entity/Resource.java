package com.city.forum.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * cityforum
 * 权限资源类
 *
 * @author : chenDW
 * @date : 2021-09-12 16:20
 **/
@Data
@Accessors(chain = true)
@TableName("resource")
public class Resource {

    /**
     * 权限资源id
     */
    @TableId
    private Integer resourceId;
    /**
     * 权限资源路径
     */
    private String resourcePath;
    /**
     * 权限资源名称
     */
    private String resourceName;
    /**
     * 权限资源类型
     * 0 菜单权限
     * 1 按钮权限
     */
    private Integer resource_type;

}
