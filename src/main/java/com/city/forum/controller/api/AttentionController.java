package com.city.forum.controller.api;

import com.city.forum.service.AttentionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * cityforum
 * 关注接口
 *
 * @author : chenDW
 * @date : 2021-10-02 15:54
 **/

@RestController
public class AttentionController extends BaseController{

    @Autowired
    private AttentionService attentionService;
    /**
     * 关注接口
     * @param attentionId 被关注的用户id
     * @return
     */
    @GetMapping("/addAttention")
    public String addAttention(@RequestParam("attentionId")Integer attentionId){
        return attentionService.addAttention(attentionId);
    }

    /**
     * 取消关注
     */
    @GetMapping("/cancelAttention")
    public String cancelAttention(@RequestParam("attentionId")Integer attentionId){
        return attentionService.cancelAttention(attentionId);
    }


}
