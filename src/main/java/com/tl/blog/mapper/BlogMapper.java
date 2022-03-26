package com.tl.blog.mapper;

import com.tl.blog.pojo.Blog;
import com.tl.blog.pojo.BlogAndTag;
import com.tl.blog.pojo.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author tl
 */
@Mapper
@Repository
public interface BlogMapper {

    Blog getBlog(Long blogId);  //后台展示博客

    Blog getDetailedBlog(@Param("id") Long blogId);  //博客详情   @Param("id")一定和sql语句中保持一致

    List<Blog> getAllBlog();

    List<Blog> getByTypeId(Long typeId);  //根据类型id获取博客

    List<Blog> getByTagId(Long tagId);  //根据标签id获取博客

    List<Blog> getIndexBlog();  //主页博客展示

    List<Blog> getAllRecommendBlog();  //推荐博客展示

    List<Blog> getSearchBlog(String query);  //全局搜索博客

    List<Blog> searchAllBlog(Blog blog);  //后台根据标题、分类、推荐搜索博客

    List<String> findGroupYear();  //查询所有年份，返回一个集合

    List<Blog> findByYear(@Param("year") String year);  //按年份查询博客

    int saveBlog(Blog blog);

    int updateBlog(Blog blog);

    int deleteBlog(Long blogId);

    int saveBlogAndTag(BlogAndTag blogAndTag);

    int deleteBlogAndTag(Long blogId);

    List<Tag> selectTags();

    //    根据博客id查询出评论数量
    int getCommentCountById(Long id);

    //更新浏览量
    int updateViews(Long id);
}
