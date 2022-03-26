package com.tl.blog.service;


import com.tl.blog.mapper.BlogMapper;
import com.tl.blog.mapper.CommentMapper;
import com.tl.blog.pojo.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * @author tl
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private BlogMapper blogMapper;

    //存放迭代找出的所有子代的集合
    private List<Comment> tempReplys = new ArrayList<>();

    @Override
    public List<Comment> listCommentByBlogId(Long blogId) {
        //查询出父节点
        List<Comment> comments = commentMapper.findByBlogIdParentIdNull(blogId, Long.parseLong("-1"));
        for(Comment comment : comments){
            Long id = comment.getId();
            String parentNickname1 = comment.getNickname();
            //            查询出子评论
            List<Comment> childComments = commentMapper.findByBlogIdParentIdNotNull(blogId,id);
            combineChildren(blogId, childComments, parentNickname1);
            comment.setReplyComments(tempReplys);
            //实现集合的复用
            tempReplys = new ArrayList<>();
        }
        return comments;
    }

    private void combineChildren(Long blogId, List<Comment> childComments, String parentNickname1) {
//        判断是否有一级子评论
        if(childComments.size() > 0){
//                循环找出子评论的id
            for(Comment childComment : childComments){
                String parentNickname = childComment.getNickname();
                childComment.setParentNickname(parentNickname1);
                tempReplys.add(childComment);
                Long childId = childComment.getId();
//                    查询出子二级评论
                recursively(blogId, childId, parentNickname);
            }
        }
    }

    //parentNickname1为要查找的子级评论的父评论昵称
    private void recursively(Long blogId, Long childId, String parentNickname1) {
//        根据子一级评论的id找到子二级评论
        List<Comment> replayComments = commentMapper.findByBlogIdAndReplayId(blogId,childId);

        if(replayComments.size() > 0){
            for(Comment replayComment : replayComments){
                //parentNickname为下一个子级的父评论昵称
                String parentNickname = replayComment.getNickname();
                replayComment.setParentNickname(parentNickname1);
                Long replayId = replayComment.getId();
                tempReplys.add(replayComment);
                recursively(blogId,replayId,parentNickname);
            }
        }
    }
    //    新增评论
    @Transactional
    @Override
    public int saveComment(Comment comment) {
        comment.setCreateTime(new Date());
        int comments = commentMapper.saveComment(comment);
//        文章评论计数
        blogMapper.getCommentCountById(comment.getBlogId());
        return comments;
    }

    //删除评论
    @Transactional
    @Override
    public void deleteComment(Comment comment,Long id) {
        commentMapper.deleteComment(id);
        blogMapper.getCommentCountById(comment.getBlogId());
    }
    //根据blog删除评论
    @Transactional
    @Override
    public void deleteCommentByBlog(Long id) {
        commentMapper.deleteCommentByBlog(id);
    }
}
