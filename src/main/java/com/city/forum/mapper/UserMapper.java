package com.city.forum.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.city.forum.model.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * cityforum
 *
 * @author : chenDW
 * @date : 2021-09-12 21:32
 **/
@Repository
public interface UserMapper extends BaseMapper<User> {

    public User selectByUserAccount(String userAccount);
}
