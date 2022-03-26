package com.tl.blog.controller;


import com.tl.blog.service.FriendLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
/**
 * @author tl
 */

@Controller
public class FriendsShowController {

    @Autowired
    private FriendLinkService friendLinkService;

    @GetMapping("/friends")
    public String friends(Model model) {
        model.addAttribute("friendlinks",friendLinkService.listFriendLink());
        return "friends";
    }

}