package com.city.forum.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.city.forum.mapper.ResourceMapper;
import com.city.forum.model.entity.Resource;

import java.util.Collection;
import java.util.Set;

/**
 * cityforum
 *
 * @author : chenDW
 * @date : 2021-09-12 22:43
 **/
public interface ResourceService extends IService<Resource> {
    /**
     * 根据用户id获取用户所有的权限
     * @param userId 用户id
     * @return  权限id集合
     */
    public Set<Integer> getResoureIdsByUserId(Integer userId);

    public void deleteResourceByType(Integer type);

    public void insertResource(Collection<Resource> resources);

}
