package com.city.forum.util;

import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * cityforum
 * 随机生成字符串
 *
 * @author : chenDW
 * @date : 2021-09-22 22:18
 **/

public class UUIDUtil {

    /**
     *  生成文件名
     * @return
     */
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
