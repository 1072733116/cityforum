package com.city.forum.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.city.forum.model.entity.Attention;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * cityforum
 *
 * @author : chenDW
 * @date : 2021-10-02 16:00
 **/
@Repository
public interface AttentionMapper extends BaseMapper<Attention> {

    /**
     * 获取用户关注的id
     */
    public Set<Integer> getAttentionIdList(Integer userId);
}
