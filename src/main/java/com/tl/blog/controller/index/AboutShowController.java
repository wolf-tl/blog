package com.tl.blog.controller.index;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
/**
 * @author tl
 */
@Controller
public class AboutShowController {

    @GetMapping("/index/about")
    public String about(){
        return "about";
    }
}
