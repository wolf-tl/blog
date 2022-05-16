package com.tl.blog.controller.admin;

import com.tl.blog.pojo.QqUser;
import com.tl.blog.service.QqUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author tl
 */
@Controller
@RequestMapping("/admin")
public class QquserController {
    @Autowired
    private QqUserService qqUserService;
    @GetMapping("/qqusers")
    public String qqusers(Model model){
        List<QqUser> list = qqUserService.check();
        model.addAttribute("qqusers",list);
        return "admin/qqusers";
    }
}
