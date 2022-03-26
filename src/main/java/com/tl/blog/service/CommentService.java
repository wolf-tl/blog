package com.tl.blog.service;

import com.tl.blog.pojo.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * @author tl
 */
public interface CommentService {


    List<Comment> listCommentByBlogId(Long blogId);

    int saveComment(Comment comment);

//    查询子评论
//    List<Comment> getChildComment(Long blogId,Long id);

    //删除评论
    void deleteComment(Comment comment,Long id);

    //根据blog删除评论
    void deleteCommentByBlog(Long id);
}
