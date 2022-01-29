package com.city.forum.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.sql.Timestamp;

/**
 * cityforum
 * 帖子对象
 *
 * @author : chenDW
 * @date : 2021-09-30 16:27
 **/
@Data
@Accessors(chain = true)
@TableName("post")
public class Post {

    //帖子id
    @TableId(value = "post_id", type = IdType.AUTO)
    private Integer postId;
    //用户id
    private Integer userId;
    //发表时间
    private long postTime;
    //发表内容
    private String postContent;
    //点赞个数
    private Integer postCount;
    //评论个数
    private Integer postComment;
    //文件类型
    private String fileType;
    //文件资源地址
    private String fileHref;



}
