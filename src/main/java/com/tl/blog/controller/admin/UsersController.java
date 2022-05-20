package com.tl.blog.controller.admin;

import com.tl.blog.pojo.MailUser;
import com.tl.blog.service.MailUserService;
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
public class UsersController {
    @Autowired
    private MailUserService mailUserService;

    @GetMapping("/users")
    public String checkoutUsers(Model model){
        List<MailUser> list = mailUserService.checkoutAll();
        model.addAttribute("list",list);
        return "admin/loginUsers";
    }
}
