package com.city.forum.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.city.forum.model.entity.Attention;

import java.util.List;
import java.util.Set;

/**
 * cityforum
 * 关注接口
 *
 * @author : chenDW
 * @date : 2021-10-02 15:59
 **/
public interface AttentionService extends IService<Attention> {
    /**
     * 添加关注
     */
    public String addAttention(Integer attentionId);

    /**
     * 取消关注
     */
    public String cancelAttention(Integer attentionId);
    /**
     * 获取用户关注的id
     */
    public Set<Integer> getAttentionList(Integer userId);
}
