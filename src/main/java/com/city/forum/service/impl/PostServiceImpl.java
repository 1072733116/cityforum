package com.city.forum.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.city.forum.data.Result;
import com.city.forum.enums.ResultCode;
import com.city.forum.exception.ApiException;
import com.city.forum.mapper.PostMapper;
import com.city.forum.mapper.PraiseMapper;
import com.city.forum.model.entity.Post;
import com.city.forum.model.entity.Praise;
import com.city.forum.model.entity.User;
import com.city.forum.model.vo.Postvo;
import com.city.forum.service.PostService;
import com.city.forum.service.PraiseService;
import com.city.forum.util.FileUtil;
import com.city.forum.util.UserUtil;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.*;

/**
 * cityforum
 *
 * @author : chenDW
 * @date : 2021-09-30 16:24
 **/
@Service
@Transactional(rollbackFor = {RuntimeException.class, ApiException.class})
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements PostService {

    @Autowired
    private UserUtil userUtil;

    @Autowired
    private FileUtil fileUtil;

    @Autowired
    private PostMapper postMapper;


    @Autowired
    private PraiseService praiseService;

    /**
     * 发表帖子
     *
     * @param content 发表内容
     * @param fileUrl   上传的文件
     * @param type    文件类型
     * @return
     */
    @Override
    public Result publicPost(String content, String fileUrl, String type) {
        Post post = new Post();
        User currentUser = userUtil.getCurrentUser();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        post.setUserId(currentUser.getUserId())
                .setPostTime(timestamp.getTime())
                .setPostContent(content)
                .setPostCount(0)
                .setPostComment(0)
                .setFileType(type)
                .setFileHref(fileUrl);
        int result = postMapper.insert(post);
        if (result != 0) {
            return new Result("发表成功");
        }
        return new Result(ResultCode.FAILED, "发表失败");
    }

    /**
     * 用户获取自己的帖子
     *
     * @return
     */
    @Override
    public Map<String, Object> getUserPosts() {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Postvo> postvoList = new ArrayList<>();
        User currentUser = userUtil.getCurrentUser();
        List<Post> userPosts = postMapper.selectList(new QueryWrapper<Post>().eq("user_id", currentUser.getUserId()));
        for (Post post : userPosts) {
            Postvo postvo = new Postvo();
            postvo.setUserId(currentUser.getUserId())
                    .setPostId(post.getPostId())
                    .setUserNickName(currentUser.getUserNickname())
                    .setUserAvatar(currentUser.getUserAvatar())
                    .setPostContent(post.getPostContent())
                    .setPostTime(post.getPostTime())
                    .setPostCount(post.getPostCount())
                    .setPostComment(post.getPostComment())
                    .setFileType(post.getFileType())
                    .setFileHref(post.getFileHref());
            postvoList.add(postvo);
        }
        setList(postvoList);
        map.put("userPosts", postvoList);
        return map;
    }

    /**
     * 通过帖子id删除帖子
     *
     * @param postId
     * @return
     */
    @Override
    public Result deletePostByPostId(Integer postId) {
        User currentUser = userUtil.getCurrentUser();
        int result = postMapper.delete(new QueryWrapper<Post>()
                .eq("user_id", currentUser.getUserId())
                .eq("post_id", postId));
        if (result != 0) {
            return new Result("删除成功");
        }

        return new Result(ResultCode.FAILED, "删除失败");
    }

    /**
     * 获取我的点赞帖子
     *
     * @return
     */
    @Override
    public Map<String, Object> getParisePorts() {
        User currentUser = userUtil.getCurrentUser();
        Map<String, Object> map = new HashMap<>();
        List<Postvo> parisePorts = postMapper.getParisePorts(currentUser.getUserId());
        setList(parisePorts);
        map.put("praisePorts", parisePorts);
        return map;
    }

    /**
     * 获取所有的帖子
     *
     * @return
     */
    @Override
    public Map<String, Object> getAllPorts() {
        Map<String, Object> map = new HashMap<>();
        Set<Postvo> allPorts = postMapper.getAllPorts();
        setList(allPorts);
        map.put("allPorts", allPorts);

        return map;
    }

    /**
     * 获取关注的帖子
     * @return
     */
    @Override
    public List<Postvo> getAttentionPosts() {
        User currentUser = userUtil.getCurrentUser();
        List<Postvo> attentionPosts = postMapper.getAttentionPosts(currentUser.getUserId());
        setList(attentionPosts);
        return attentionPosts;
    }

    /**
     * 我评论的帖子
     * @return
     */
    @Override
    public List<Postvo> getPostsComment() {
        User currentUser = userUtil.getCurrentUser();
        List<Postvo> postsComment = postMapper.getPostsComment(currentUser.getUserId());
        setList(postsComment);
        return postsComment;
    }

    public void setList(Collection<Postvo> list){
        for (Postvo postvo :list){
            String[] split = postvo.getFileHref().split("#");
            List<String> fileList = new ArrayList<>();
            for (String s:split){
                fileList.add(s);
            }
            postvo.setFileList(fileList);
        }
    }

}
