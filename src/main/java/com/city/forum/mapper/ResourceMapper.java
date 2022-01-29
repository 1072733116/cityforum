package com.city.forum.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.city.forum.model.entity.Resource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Set;

/**
 * cityforum
 *
 * @author : chenDW
 * @date : 2021-09-12 22:37
 **/
@Repository
public interface ResourceMapper extends BaseMapper<Resource> {
    /**
     * 根据用户id获取该用户的所有权限id
     * @param userId 用户id
     * @return 权限id集合
     */
    public Set<Integer> selectResoureIdsByUserId(Integer userId);

    /**
     * 批量新增权限资源
     * @param resources 资源对象集合
     * @return 受影响的行数
     */
    public Integer insertResources(@Param("resources") Collection<Resource> resources);
}
