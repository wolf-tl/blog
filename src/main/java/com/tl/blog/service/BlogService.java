package com.tl.blog.service;

import com.tl.blog.pojo.Blog;
import com.tl.blog.pojo.BlogAndTag;
import com.tl.blog.pojo.Tag;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.javassist.NotFoundException;

import java.util.List;
import java.util.Map;
/**
 * @author tl
 */
public interface BlogService {
    Blog getBlog(Long id);  //后台展示博客

    Blog getDetailedBlog(@Param("id") Long id) throws NotFoundException;  //博客详情

    List<Blog> getAllBlog();

    List<Blog> getByTypeId(Long typeId);  //根据类型id获取博客

    List<Blog> getByTagId(Long tagId);  //根据标签id获取博客

    List<Blog> getIndexBlog();  //主页博客展示

    List<Blog> getAllRecommendBlog();  //推荐博客展示

    List<Blog> getSearchBlog(String query);  //全局搜索博客

    List<Blog> searchAllBlog(Blog blog);  //后台根据标题、分类、推荐搜索博客

    List<String> findGroupYear();  //查询所有年份，返回一个集合

    List<Blog> findByYear(@Param("year") String year);  //按年份查询博客

    Map<String,List<Blog>> archiveBlog();  //归档博客

    int saveBlog(Blog blog);

    int updateBlog(Blog blog);

    int deleteBlog(Long id);

    int deleteBlogAndTag(Long blogId);

    List<Tag> selectTags();

    int countBlog();  //查询博客条数

}
