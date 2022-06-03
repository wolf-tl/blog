package com.tl.blog.controller.index;


import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tl.blog.pojo.Blog;
import com.tl.blog.pojo.Comment;
import com.tl.blog.pojo.Tag;
import com.tl.blog.pojo.Type;
import com.tl.blog.service.BlogService;
import com.tl.blog.service.CommentService;
import com.tl.blog.service.TagService;
import com.tl.blog.service.TypeService;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
/**
 * @author tl
 */
@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/index")
    public String toIndex(@RequestParam(required = false,defaultValue = "1",value = "pagenum")int pagenum, Model model){

        PageHelper.startPage(pagenum, 8);
        List<Blog> allBlog = blogService.getIndexBlog();

        List<Type> allType = typeService.getBlogType();  //获取博客的类型(联表查询)

        List<Tag> allTag = tagService.getBlogTag();  //获取博客的标签(联表查询)

        List<Blog> recommendBlog =blogService.getAllRecommendBlog();  //获取推荐博客

        //得到分页结果对象
        PageInfo pageInfo = new PageInfo(allBlog);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("tags", allTag);
        model.addAttribute("types", allType);
        model.addAttribute("recommendBlogs", recommendBlog);
        return "index";
    }

    @GetMapping("/index/search")
    public String search(@RequestParam(required = false,defaultValue = "1",value = "pagenum")int pagenum,
                         @RequestParam String query, Model model){

        PageHelper.startPage(pagenum, 8);
        List<Blog> searchBlog = blogService.getSearchBlog(query);
        PageInfo pageInfo = new PageInfo(searchBlog);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("query", query);
        return "search";
    }

    @GetMapping("/index/blog/{id}")
    public String toLogin(@PathVariable Long id, Model model) throws NotFoundException {
        Blog blog = blogService.getDetailedBlog(id);
        List<Comment> comments = commentService.listCommentByBlogId(id);
        model.addAttribute("comments", comments);
        model.addAttribute("blog", blog);
        return "blog";
    }
}
