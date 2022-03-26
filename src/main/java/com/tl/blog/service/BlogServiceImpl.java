package com.tl.blog.service;


import com.tl.blog.mapper.BlogMapper;
import com.tl.blog.pojo.Blog;
import com.tl.blog.pojo.BlogAndTag;
import com.tl.blog.pojo.Tag;
import com.tl.blog.utils.MarkdownUtils;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
/**
 * @author tl
 */
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;

    @Override
    public Blog getBlog(Long id) {
        return blogMapper.getBlog(id);
    }

    @Transactional
    @Override
    public Blog getDetailedBlog(Long id) throws NotFoundException {
        Blog blog = blogMapper.getDetailedBlog(id);
        if (blog == null) {
            throw new NotFoundException("该博客不存在");
        }
        String content = blog.getContent();
        blog.setContent(MarkdownUtils.markdownToHtmlExtensions(content));  //将Markdown格式转换成html
        //文章访问数量自增  放在blog查询后
        blogMapper.updateViews(id);
        //文章评论数量更新
        blogMapper.getCommentCountById(id);
        return blog;
    }

    @Override
    public List<Blog> getAllBlog() {
        return blogMapper.getAllBlog();
    }

    @Override
    public List<Blog> getByTypeId(Long typeId) {
        return blogMapper.getByTypeId(typeId);
    }

    @Override
    public List<Blog> getByTagId(Long tagId) {
        return blogMapper.getByTagId(tagId);
    }

    @Override
    public List<Blog> getIndexBlog() {
        return blogMapper.getIndexBlog();
    }

    @Override
    public List<Blog> getAllRecommendBlog() {
        return blogMapper.getAllRecommendBlog();
    }

    @Override
    public List<Blog> getSearchBlog(String query) {
        return blogMapper.getSearchBlog(query);
    }

    @Override
    public List<Blog> searchAllBlog(Blog blog) {
        return blogMapper.searchAllBlog(blog);
    }

    @Override
    public List<String> findGroupYear() {
        return blogMapper.findGroupYear();
    }

    @Override
    public List<Blog> findByYear(String year) {
        return blogMapper.findByYear(year);
    }

    @Transactional
    @Override    //新增博客
    public int saveBlog(Blog blog) {
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setViews(0);
        //保存博客
        blogMapper.saveBlog(blog);
        //保存博客后才能获取自增的id
        Long id = blog.getId();
        //将标签的数据存到t_blogs_tag表中
        List<Tag> tags = blog.getTags();
        BlogAndTag blogAndTag = null;
        for (Tag tag : tags) {
            //新增时无法获取自增的id,在mybatis里修改
            blogAndTag = new BlogAndTag(tag.getId(), id);
            //t_blog_tags表中无主键，可以重复
            blogMapper.saveBlogAndTag(blogAndTag);
        }
        return 1;
    }

    @Transactional
    @Override   //编辑博客
    public int updateBlog(Blog blog) {
        blog.setUpdateTime(new Date());//更新日期
        //将标签的数据存到t_blogs_tag表中
        List<Tag> tags = blog.getTags();
        //删除对应的中间表数据
        blogMapper.deleteBlogAndTag(blog.getId());
        BlogAndTag blogAndTag = null;
        for (Tag tag : tags) {
            blogAndTag = new BlogAndTag(tag.getId(), blog.getId());
            blogMapper.saveBlogAndTag(blogAndTag);
        }
        return blogMapper.updateBlog(blog);
    }
    //归档
    @Override
    public Map<String, List<Blog>> archiveBlog() {
        List<String> years = blogMapper.findGroupYear();
        Set<String> set = new HashSet<>(years);  //set去掉重复的年份
        Map<String, List<Blog>> map = new HashMap<>();
        for (String year : set) {
            map.put(year, blogMapper.findByYear(year));
        }
        return map;
    }

    @Transactional
    @Override
    public int deleteBlog(Long id) {
        return blogMapper.deleteBlog(id);
    }

    @Transactional
    @Override
    public int deleteBlogAndTag(Long blogId) {
        return blogMapper.deleteBlogAndTag(blogId);
    }

    @Transactional
    @Override
    public List<Tag> selectTags() {
        return blogMapper.selectTags();
    }

    @Override
    public int countBlog() {
        return blogMapper.getAllBlog().size();
    }

}
