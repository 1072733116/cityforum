package com.city.forum.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * cityforum
 * 评论对象
 *
 * @author : chenDW
 * @date : 2021-10-03 08:59
 **/
@Data
@Accessors(chain = true)
@TableName("comment")
public class Comment {
    /**
     * 评论id
     */
    @TableId(value = "comment_id",type = IdType.AUTO)
    private Integer commentId;
    /**
     * 帖子id
     */
    private Integer postId;
    /**
     * 指向父评论的id
     */
    private Integer parentId;
    /**
     * 评论内容
     */
    private String content;
    /**
     * 评论的用户id
     */
    private Integer userId;
    /**
     * 评论时间
     */
    private long date;
    /**
     * 评论状态 0删除 1未删除
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer commentStatus;
}
