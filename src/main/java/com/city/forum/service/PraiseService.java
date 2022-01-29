package com.city.forum.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.city.forum.model.entity.Praise;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * cityforum
 * 点赞处理
 *
 * @author : chenDW
 * @date : 2021-10-01 09:39
 **/
public interface PraiseService extends IService<Praise> {

    /**
     * 增加点赞
     */
    public String addPraise(Integer postId);

    /**
     * 取消点赞
     * @param postId
     * @return
     */
    public String cancelPraise(Integer postId);
    /**
     * 获取点赞的帖子id
     */
    public Set<Integer> getPraisePostIds(Integer userId);
}
