package com.shijie.dao;

import com.shijie.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CommentDao {

    List<Comment> queryAllCommentByBlogID(int blog_id);

    int addComment(Comment comment);

    int deleteCommentByID(int comment_id);

}
