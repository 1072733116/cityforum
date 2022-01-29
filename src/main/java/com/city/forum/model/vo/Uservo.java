package com.city.forum.model.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Set;

/**
 * cityforum
 * 返回用户的数据对象
 *
 * @author : chenDW
 * @date : 2021-09-12 22:02
 **/
@Data
@Accessors(chain = true)
public class Uservo {
    /**
     * 主键id
     */
    private Integer uservoId;
    /**
     * String account
     */
    private String uservoAccount;
    /**
     * 用户头像地址
     */
    private String uservoAvatar;

    /**
     * 用户昵称
     */
    private String uservoNickname;
    /**
     * 用户性别
     */
    private String uservoSex;
    /**
     * 用户个性签名
     */
    private String uservoSign;
    /**
     * 用户登录token
     */
    private String token;
    /**
     * 用户权限集合
     */
    private Set<Integer> resourceIds;
    /**
     * 用户关注的id
     */
    private Set<Integer> attendtionIds;
    /**
     * 用户点赞的帖子id
     */
    private Set<Integer> praisePostIds;
}
