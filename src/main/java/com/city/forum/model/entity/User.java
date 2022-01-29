package com.city.forum.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.city.forum.annotation.ExceptionCode;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * cityforum
 * 用户实体类
 *
 * @author : chenDW
 * @date : 2021-09-12 15:58
 **/
@Data
@TableName("user")
@Accessors(chain = true)
public class User {

    /**
     * 主键用户id
     */
    @TableId(type = IdType.AUTO)
    private Integer userId;
    /**
     * 用户帐号
     */
    @NotNull(message = "用户帐号不能为空")
    @Size(min = 11,max = 11,message = "请输入正确的手机号码")
    @ExceptionCode(msg = "帐号验证失败")
    private String userAccount;
    /**
     * 用户密码
     */
    @NotNull(message = "用户密码不能为空")
    @Size(min = 6,max = 13,message = "密码长度必须是6-11个字符")
    @ExceptionCode(msg = "密码验证错误")
    private String userPassword;
    /**
     * 用户昵称
     */
    public String userNickname;
    /**
     * 用户头像url
     */
    private String userAvatar;
    /**
     * 用户性别
     */
    private String userSex;
    /**
     * 用户个性签名
     */
    private String userSign;


}
