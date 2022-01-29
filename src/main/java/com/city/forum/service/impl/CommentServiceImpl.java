package com.city.forum.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.city.forum.exception.ApiException;
import com.city.forum.mapper.CommentMapper;
import com.city.forum.mapper.PostMapper;
import com.city.forum.model.entity.Comment;
import com.city.forum.model.entity.Post;
import com.city.forum.model.entity.User;
import com.city.forum.model.vo.Commentvo;
import com.city.forum.service.CommentService;
import com.city.forum.util.UserUtil;
import io.jsonwebtoken.lang.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.*;

/**
 * cityforum
 *
 * @author : chenDW
 * @date : 2021-10-03 09:21
 **/
@Service
@Transactional(rollbackFor = {RuntimeException.class,ApiException.class})
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Autowired
    private UserUtil userUtil;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private PostMapper postMapper;
    /**
     * 保存评论或回复
     * @param postId 帖子id
     * @param parentId  评论父id
     * @param content 评论内容
     * @return
     */
    @Override
    public String saveComment(Integer postId, Integer parentId, String content) {
        if (postId == null ){
            throw new ApiException("帖子不存在");
        }
        User currentUser = userUtil.getCurrentUser();
        Comment comment = new Comment();
        comment.setPostId(postId)
                .setUserId(currentUser.getUserId())
                .setContent(content)
                .setDate(System.currentTimeMillis())
                .setCommentStatus(1);
        if (parentId != null){
            comment.setParentId(parentId);
        }
        int insert = commentMapper.insert(comment);
        Post post = postMapper.selectById(postId);
        post.setPostComment(post.getPostComment()+1);
        int update = postMapper.updateById(post);
        if (update == 0 || insert == 0){
            throw new ApiException("评论失败");
        }
        return "评论成功";
    }

    /**
     * 删除评论
     * @param commentId 评论id
     * @return
     */
    @Override
    public String deleteComment(Integer commentId,Integer postId) {
        User currentUser = userUtil.getCurrentUser();
        int delete = commentMapper.delete(new QueryWrapper<Comment>().eq("comment_id", commentId).eq("user_id", currentUser.getUserId()));
        Post post = postMapper.selectById(postId);
        post.setPostComment(post.getPostComment()-1);
        int update = postMapper.updateById(post);
        if (delete == 0 || update == 0){
            throw new ApiException("删除失败");
        }
        return "删除成功";
    }

    /**
     * 获取评论
     * @param postId 帖子id
     * @return
     */
    @Override
    public List<Commentvo> getComment(Integer postId) {
        List<Commentvo> allComment = commentMapper.getCommentvoList(postId);
        if (Collections.isEmpty(allComment)){
            return new ArrayList<>();
        }
        Map<Integer,Commentvo> map = new HashMap<>();
        List<Commentvo> result = new ArrayList<>();
        for (Commentvo commentvo : allComment){
            if (commentvo.getParentId() == null){
                result.add(commentvo);
            }
            map.put(commentvo.getCommentId(),commentvo);
        }
        for (Commentvo c : allComment){
            if (c.getParentId() != null){
                Commentvo parent = map.get(c.getParentId());
                if (parent.getChild()==null){
                    parent.setChild(new ArrayList<Commentvo>());
                }
                parent.getChild().add(c);
            }
        }
        return result;
    }
}
