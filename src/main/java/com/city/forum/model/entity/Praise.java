package com.city.forum.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * cityforum
 *
 * @author : chenDW
 * @date : 2021-10-01 09:31
 **/
@Data
@Accessors(chain = true)
@TableName("praise")
public class Praise {
    /**
     * 点赞id
     */
    @TableId(value = "praise_id", type = IdType.AUTO)
    private Integer praiseId;
    /**
     * 点赞用户
     */
    private Integer userId;
    /**
     * 帖子id
     */
    private Integer postId;
    /**
     * 点赞状态
     * 1 点赞
     * 0 取消点赞
     */
    private Integer praiseStatus;
}
