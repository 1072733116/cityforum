package com.city.forum.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * cityforum
 * 关注对象
 *
 * @author : chenDW
 * @date : 2021-10-02 15:54
 **/
@Data
@Accessors(chain = true)
@TableName("attention")
public class Attention {

    /**
     * id
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 被关注用户id
     */
    private Integer attentionId;
    /**
     * 关注状态
     */
    private Integer attentionStatus;
}

