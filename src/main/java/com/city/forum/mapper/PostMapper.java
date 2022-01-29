package com.city.forum.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.city.forum.model.entity.Post;
import com.city.forum.model.vo.Postvo;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Set;

/**
 * cityforum
 * 帖子
 *
 * @author : chenDW
 * @date : 2021-09-30 16:50
 **/
@Repository
public interface PostMapper extends BaseMapper<Post> {

    /**
     * 获取我的点赞帖子
     */
    public List<Postvo> getParisePorts(Integer userId);
    /**
     * 返回所有帖子
     * @return
     */
    public Set<Postvo> getAllPorts();
    /**
     * 获取我关注的帖子
     */
    public List<Postvo> getAttentionPosts(Integer userId);
    /**
     * 获取我评论的帖子
     */
    public List<Postvo> getPostsComment(Integer userId);
}
