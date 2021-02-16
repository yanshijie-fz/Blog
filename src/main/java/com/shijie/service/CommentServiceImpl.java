package com.shijie.service;

import com.alibaba.fastjson.JSONObject;
import com.shijie.dao.CommentDao;
import com.shijie.pojo.Comment;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    CommentDao commentDao;

    @Override
    public List<Comment> queryAllCommentByBlogID(int blog_id) {
        List<Comment> comments=commentDao.queryAllCommentByBlogID(blog_id);
        List<Comment> comment_seconds = new ArrayList<>();
        List<Comment> comment_firsts = new ArrayList<>();
        for (Comment comment : comments) {
            if (comment.getPid()==0){
                comment_firsts.add(comment);
            } else{
                comment_seconds.add(comment);
            }
        }

        for (Comment comment_first : comment_firsts) {
            List<Comment> Replys= new ArrayList<>();
            long comment_first_id=comment_first.getComment_id();
            for (Comment comment_second : comment_seconds) {
                if (comment_second.getPid()==comment_first_id){
                    Replys.add(comment_second);
                }
            }
            comment_first.setReplys(Replys);
        }
        return comment_firsts;
    }

    @Override
    public int addComment(Comment comment) {
        return commentDao.addComment(comment);
    }

    @Override
    public int deleteCommentByID(int comment_id) {
        return commentDao.deleteCommentByID(comment_id);
    }
}
