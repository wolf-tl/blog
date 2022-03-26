package com.tl.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
/**
 * @author tl
 */
@Controller
public class AboutShowController {

    @GetMapping("/about")
    public String about(){
        return "about";
    }
}