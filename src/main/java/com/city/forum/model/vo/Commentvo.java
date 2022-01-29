package com.city.forum.model.vo;

import com.city.forum.model.entity.Comment;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * cityforum
 * 评论返回对象
 *
 * @author : chenDW
 * @date : 2021-10-03 09:06
 **/
@Data
@Accessors(chain = true)
public class Commentvo {

    /**
     * 评论id
     */
    private Integer commentId;
    /**
     * 帖子id
     */
    private Integer postId;
    /**
     * 父评论的id
     */
    private Integer parentId;
    /**
     * 评论用户id
     */
    private Integer userId;
    /**
     * 评论用户昵称
     */
    private String userNickname;
    /**
     * 用户头像资源地址
     */
    private String userAvatar;

    /**
     * 评论时间
     */
    private long date;
    /**
     * 评论内容
     */
    private String content;
    /**
     * 子评论
     */
    private List<Commentvo> child;

}
