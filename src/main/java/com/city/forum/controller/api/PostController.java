package com.city.forum.controller.api;

import com.city.forum.data.Result;
import com.city.forum.model.entity.Post;
import com.city.forum.model.vo.Postvo;
import com.city.forum.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * cityforum
 * 发表帖子
 *
 * @author : chenDW
 * @date : 2021-09-30 16:04
 **/
@RestController
public class PostController extends BaseController {

    @Autowired
    private PostService postService;

    /**
     * 获取所有的帖子
     *
     * @return
     */
    @GetMapping("/getAllPosts")
    public Map<String, Object> getAllPorts() {
        return postService.getAllPorts();
    }

    /**
     * 获取用户的帖子
     *
     * @return
     */
    @GetMapping("/getUserPosts")
    public Map<String, Object> getUserPostsById() {
        return postService.getUserPosts();
    }

    /**
     * 获取我的点赞的帖子
     *
     * @return
     */
    @GetMapping("/getPraisePosts")
    public Map<String, Object> getPraisePosts() {
        return postService.getParisePorts();
    }

    /**
     * 发表帖子
     *
     * @param content
     * @param fileUrl
     * @param type
     * @return
     */
    @PostMapping("/addPost")
        public Result addPost(@RequestParam("content") String content, @RequestParam(value = "fileUrl",required = false)String fileUrl, @RequestParam(value = "type",required = false) String type) {
        return postService.publicPost(content, fileUrl, type);
    }

    /**
     * 删除帖子
     *
     * @param postId
     * @return
     */
    @DeleteMapping("deletePost")
    public Result deletePostByPostId(@RequestParam("postId") Integer postId) {
        return postService.deletePostByPostId(postId);
    }

    /**
     * 获取我关注的帖子
     */
    @GetMapping("/getAttentionPosts")
    public List<Postvo> getAttentionPosts(){
        return postService.getAttentionPosts();
    }

    /**
     * 获取我评论的帖子
     */
    @GetMapping("/getPostsComment")
    public List<Postvo> getPostsComment(){
        return postService.getPostsComment();
    }

}
