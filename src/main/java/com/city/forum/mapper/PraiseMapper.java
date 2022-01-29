package com.city.forum.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.city.forum.model.entity.Praise;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * cityforum
 * 点赞处理
 *
 * @author : chenDW
 * @date : 2021-10-01 09:30
 **/
@Repository
public interface PraiseMapper extends BaseMapper<Praise> {

}
