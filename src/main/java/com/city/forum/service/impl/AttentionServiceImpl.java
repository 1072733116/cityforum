package com.city.forum.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.city.forum.exception.ApiException;
import com.city.forum.mapper.AttentionMapper;
import com.city.forum.model.entity.Attention;
import com.city.forum.model.entity.User;
import com.city.forum.service.AttentionService;
import com.city.forum.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * cityforum
 *
 * @author : chenDW
 * @date : 2021-10-02 15:59
 **/
@Service
@Transactional(rollbackFor = {RuntimeException.class, ApiException.class})
public class AttentionServiceImpl extends ServiceImpl<AttentionMapper, Attention> implements AttentionService {

    @Autowired
    private UserUtil userUtil;

    @Autowired
    private AttentionMapper attentionMapper;

    /**
     * 添加关注
     * @param attentionId 被关注用户ID
     * @return
     */
    @Override
    public String addAttention(Integer attentionId) {
        User currentUser = userUtil.getCurrentUser();
        Attention attention = attentionMapper.selectOne(new QueryWrapper<Attention>()
                .eq("user_id", currentUser.getUserId())
                .eq("attention_id", attentionId));
        if (attention != null){
            attention.setAttentionStatus(1);
            int i = attentionMapper.updateById(attention);
            if (i == 0){
                throw new ApiException("关注失败");
            }
        }else{
            Attention newAttention = new Attention();
            newAttention.setUserId(currentUser.getUserId())
                    .setAttentionId(attentionId)
                    .setAttentionStatus(1);
            int insert = attentionMapper.insert(newAttention);
            if (insert ==0 ){
                throw new ApiException("关注失败");
            }
        }
        return "关注成功";
    }

    /**
     * 取消关注
     * @param attentionId
     * @return
     */
    @Override
    public String cancelAttention(Integer attentionId) {
        User currentUser = userUtil.getCurrentUser();
        Attention attention = attentionMapper.selectOne(new QueryWrapper<Attention>()
                .eq("user_id", currentUser.getUserId())
                .eq("attention_id", attentionId));
        if (attention == null){
            throw new ApiException();
        }
        attention.setAttentionStatus(0);
        int i = attentionMapper.updateById(attention);
        if (i == 0){
            throw new ApiException("取消失败");
        }
        return "取消成功";
    }

    /**
     * 获取关注的id
     * @return
     */
    @Override
    public Set<Integer> getAttentionList(Integer userId) {
        return attentionMapper.getAttentionIdList(userId);
    }


}
