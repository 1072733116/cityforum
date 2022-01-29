package com.city.forum.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.city.forum.exception.ApiException;
import com.city.forum.mapper.PostMapper;
import com.city.forum.mapper.PraiseMapper;
import com.city.forum.model.entity.Post;
import com.city.forum.model.entity.Praise;
import com.city.forum.model.entity.User;
import com.city.forum.service.PraiseService;
import com.city.forum.util.UserUtil;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * cityforum
 *
 * @author : chenDW
 * @date : 2021-10-01 09:40
 **/
@Service
@Transactional(rollbackFor = {ApiException.class})
public class PraiseServiceImpl extends ServiceImpl<PraiseMapper, Praise> implements PraiseService {

    @Autowired
    private UserUtil userUtil;

    @Autowired
    private PraiseMapper praiseMapper;

    @Autowired
    private PostMapper postMapper;

    @Override
    public String addPraise(Integer postId) {
        User currentUser = userUtil.getCurrentUser();
        Post post = postMapper.selectOne(new QueryWrapper<Post>().eq("post_id", postId));
        if (post == null) {
            throw new ApiException("帖子不存在");
        }
        //如果已经存在
        Praise praise1 = praiseMapper.selectOne(new QueryWrapper<Praise>().eq("post_id", postId).eq("user_id", currentUser.getUserId()));
        if (praise1 != null) {
            praise1.setPraiseStatus(1);
            int i = praiseMapper.updateById(praise1);
            post.setPostCount(post.getPostCount() + 1);
            int count = postMapper.updateById(post);
            if (i == 0 || count == 0) {
                throw new ApiException("点赞失败");
            }
        } else {
            Praise praise = new Praise();
            praise.setUserId(currentUser.getUserId())
                    .setPostId(postId)
                    .setPraiseStatus(1);
            int insert = praiseMapper.insert(praise);
            post.setPostCount(post.getPostCount() + 1);
            int count = postMapper.update(post, new QueryWrapper<Post>().eq("post_id", postId));
            if (insert == 0 || count == 0) {
                throw new ApiException("点赞失败");
            }
        }
        return "点赞成功";
    }

    /**
     * 取消点赞
     * @param postId 帖子id
     * @return
     */
    @Override
    public String cancelPraise(Integer postId) {
        User currentUser = userUtil.getCurrentUser();
        Post post = postMapper.selectOne(new QueryWrapper<Post>().eq("post_id", postId));
        Praise praise = praiseMapper.selectOne(new QueryWrapper<Praise>().eq("post_id", postId).eq("user_id", currentUser.getUserId()));
        praise.setPraiseStatus(0);
        int result = praiseMapper.updateById(praise);
        post.setPostCount(post.getPostCount() - 1);
        int count = postMapper.updateById(post);
        if (result == 0 || count == 0) {
            throw new ApiException("取消失败");
        }
        return "取消成功";
    }

    /**
     * 获取点赞的帖子id
     * @return
     */
    @Override
    public Set<Integer> getPraisePostIds(Integer userId) {
        List<Praise> praises = baseMapper.selectList(new QueryWrapper<Praise>().eq("user_id", userId).eq("praise_status", 1));
        Set<Integer> praisePostIds = new HashSet<>();
        for (Praise p : praises){
            praisePostIds.add(p.getPostId());
        }
        return  praisePostIds;
    }

}
