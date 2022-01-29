package com.city.forum.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * cityforum
 * 返回的帖子对象
 *
 * @author : chenDW
 * @date : 2021-10-01 16:50
 **/
@Data
@Accessors(chain = true)
public class Postvo {

    /**
     * 帖子id
     */
    private Integer postId;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 用户昵称
     */
    private String userNickName;
    /**
     * 用户头像地址
     */
    private String userAvatar;
    /**
     * 时间戳
     */
    private long postTime;
    /**
     * 帖子内容
     */
    private String postContent;
    /**
     * 点赞个数
     */
    private Integer postCount;
    /**
     * 评论个数
     */
    private Integer postComment;
    /**
     * 文件类型
     */
    private String fileType;
    /**
     * 文件资源
     */
    private String fileHref;
    /**
     * 文件资源数组
     */
    private List<String> fileList;
}
