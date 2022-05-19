package com.tl.blog.controller.userLogin;

import com.tl.blog.pojo.MailUser;
import com.tl.blog.service.MailService;
import com.tl.blog.service.MailUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.concurrent.TimeUnit;
import java.util.jar.Attributes;

/**
 * @author tl
 */
@Controller
@RequestMapping("/mail")
public class MailUserController {
    @Autowired
    private MailUserService mailUserService;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private MailService mailService;

    private String code;

    @GetMapping("/")
    public String loginPage(){
        return "mailLogin/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String mail, HttpSession session){
        MailUser user = mailUserService.checkout(mail);
        if(user!=null){
            session.setAttribute("mailuser", user);
        }else{
            return "redirect:/mail";
        }
        return "index";
    }

    // 发送验证码
    @PostMapping("/sendCode")
    public String sendCode(@RequestParam String mail, RedirectAttributes attributes){
        // 检测该用户是否已经注册过
        MailUser user = mailUserService.checkout(mail);
        if(user!=null){
            attributes.addFlashAttribute("msg","该用户已被注册！");
            return "redirect:/mail/sendCode";
        }else{
            String ss = redisTemplate.opsForValue().get(mail);
            if (!StringUtils.isEmpty(ss)){
                // 提示 邮件已发送

            }else{
                code = mailService.sendMail(mail);

                // 添加缓存并设置五分钟有效
                redisTemplate.opsForValue().set(mail,code,5, TimeUnit.MINUTES);
            }
        }
        return null;
    }

    @PostMapping("/register")
    public String register(@RequestParam String mail, @RequestParam String randomNum, RedirectAttributes attributes){
        // 注册新用户
        if(!code.equals(randomNum)){
            attributes.addFlashAttribute("msg","验证码错误！");
            return "redirect:/mail";
        }
        MailUser mailuser = new MailUser();
        mailuser.setMail(mail);
        mailUserService.addMail(mailuser);
        attributes.addFlashAttribute("msg","注册成功！");
        // 返回注册成功界面
        return "mailLogin/success";
    }
}
