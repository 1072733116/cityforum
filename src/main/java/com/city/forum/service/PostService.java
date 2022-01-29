package com.city.forum.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.city.forum.data.Result;
import com.city.forum.model.entity.Post;
import com.city.forum.model.vo.Postvo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;


/**
 * cityforum
 * 发表帖子
 *
 * @author : chenDW
 * @date : 2021-09-30 16:23
 **/
public interface PostService extends IService<Post> {
    /**
     *发表帖子
     */
    public Result publicPost(String content, String fileUrl, String type);

    /**
     * 获取自己的帖子
     * @return
     */
    public Map<String, Object> getUserPosts();

    /**
     * 删除自己的帖子
     * @param postId
     * @return
     */
    public Result deletePostByPostId(Integer postId);

    /**
     * 获取我的点赞的帖子
     * @return
     */
    public Map<String,Object> getParisePorts();

    /**
     * 获取所有的帖子
     * @return
     */
    public Map<String,Object> getAllPorts();

    /**
     *  获取用户关注的帖子
     */
    public List<Postvo> getAttentionPosts();

    /**
     * 获取用户评论的帖子
     */
    public List<Postvo> getPostsComment();
}
