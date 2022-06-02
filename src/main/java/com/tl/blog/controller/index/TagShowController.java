package com.tl.blog.controller.index;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tl.blog.pojo.Blog;
import com.tl.blog.pojo.Tag;
import com.tl.blog.service.BlogService;
import com.tl.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
/**
 * @author tl
 */
@Controller
public class TagShowController {

    @Autowired
    private TagService tagService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/index/tags/{id}")
    public String types(@PathVariable Long id, @RequestParam(required = false,defaultValue = "1",value = "pagenum")int pagenum,
                        Model model){
        List<Tag> tags = tagService.getBlogTag();
        //-1从导航点过来的
        if (id == -1){
            id = tags.get(0).getId();
        }
        PageHelper.startPage(pagenum, 8);  //开启分页
        List<Blog> blogs = blogService.getByTagId(id);
        PageInfo pageInfo = new PageInfo(blogs);
        model.addAttribute("tags", tags);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("activeTagId", id);

        return "tags";
    }
}
