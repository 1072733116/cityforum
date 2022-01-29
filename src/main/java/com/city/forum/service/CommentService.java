package com.city.forum.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.city.forum.model.entity.Comment;
import com.city.forum.model.vo.Commentvo;

import java.util.List;

/**
 * cityforum
 * 评论接口
 *
 * @author : chenDW
 * @date : 2021-10-03 09:19
 **/
public interface CommentService extends IService<Comment> {

    /**
     * 添加评论或者回复评论
     * @param postId
     * @param parentId
     * @param content
     * @return
     */
   public String saveComment(Integer postId, Integer parentId, String content);

    /**
     * 删除评论
     * @param CommentId 评论id
     * @return
     */
    public String deleteComment(Integer CommentId,Integer postId);

    /**
     * 获取评论
     */
    public List<Commentvo> getComment(Integer postId);
}
