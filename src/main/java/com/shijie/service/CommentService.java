package com.shijie.service;

import com.alibaba.fastjson.JSONObject;
import com.shijie.pojo.Comment;

import java.util.List;

public interface CommentService {
    List<Comment>  queryAllCommentByBlogID(int blog_id);

    int addComment(Comment comment);

    int deleteCommentByID(int comment_id);
}
