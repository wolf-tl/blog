package com.tl.blog.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tl.blog.pojo.Blog;
import com.tl.blog.pojo.Type;
import com.tl.blog.service.BlogService;
import com.tl.blog.service.TypeService;
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
public class TypeShowController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/types/{id}")
    public String types(@PathVariable Long id, @RequestParam(required = false,defaultValue = "1",value = "pagenum")int pagenum,
                        Model model){

        List<Type> types = typeService.getBlogType();

        //-1从导航点过来的
        if (id == -1){
            id = types.get(0).getId();
        }
        PageHelper.startPage(pagenum, 8);  //开启分页
        List<Blog> blogs = blogService.getByTypeId(id);
        PageInfo pageInfo = new PageInfo(blogs);
        model.addAttribute("types", types);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("activeTypeId", id);

        return "types";
    }
}
