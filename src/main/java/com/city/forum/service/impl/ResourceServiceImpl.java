package com.city.forum.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.city.forum.mapper.ResourceMapper;
import com.city.forum.model.entity.Resource;
import com.city.forum.service.ResourceService;
import io.jsonwebtoken.lang.Collections;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Set;

/**
 * cityforum
 *
 * @author : chenDW
 * @date : 2021-09-12 22:44
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements ResourceService {

    @Override
    public Set<Integer> getResoureIdsByUserId(Integer userId) {
        return baseMapper.selectResoureIdsByUserId(userId);
    }

    @Override
    public void deleteResourceByType(Integer type) {
        // 先删除所有接口类型的资源
        LambdaUpdateWrapper<Resource> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(Resource::getResource_type,type);
        baseMapper.delete(wrapper);
    }

    @Override
    public void insertResource(Collection<Resource> resources) {
        if (Collections.isEmpty(resources)){
            return;
        }
        // 再新增接口类型的资源
        baseMapper.insertResources(resources);
    }
}
