package com.city.forum.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.city.forum.model.entity.Comment;
import com.city.forum.model.vo.Commentvo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * cityforum
 * 评论处理
 *
 * @author : chenDW
 * @date : 2021-10-03 09:17
 **/
@Repository
public interface CommentMapper extends BaseMapper<Comment> {

    /**
     * 获取所有的评论
     */
    public List<Commentvo> getCommentvoList(Integer postId);
}
