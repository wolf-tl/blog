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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.concurrent.TimeUnit;


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

    private String mail;

    // 转到登录界面
    @GetMapping()
    public String loginPage(){
        return "mailLogin/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String mail, HttpSession session, RedirectAttributes attributes){
        MailUser user = mailUserService.checkout(mail);
        if(user!=null){
            session.setAttribute("mailuser", user);
            user.setNum(user.getNum()+1);
            user.setVisitTime(new Date());
            mailUserService.update(user);
            return "redirect:/";
        }else{
            attributes.addFlashAttribute("msg","该用户尚未注册！");
            return "redirect:/mail";
        }
    }

    @GetMapping("/toRegister")
    public String toRegister(){
        return "mailLogin/sendMail";
    }

    // 发送验证码
    @PostMapping("/sendCode")
    public String sendCode(@RequestParam String mail, HttpServletRequest request){
        // 检测该用户是否已经注册过
        MailUser user = mailUserService.checkout(mail);
        if(user!=null){
            request.setAttribute("msg","该用户已被注册！");
        }else{
            String ss = redisTemplate.opsForValue().get(mail);
            // 缓存中是否为空
            if (!StringUtils.hasText(ss)){
                // 提示 邮件已发送
                request.setAttribute("msg","验证码已发送，请稍后再试！");
            }else{
                code = mailService.sendMail(mail);
                this.mail = mail;

                // 添加缓存并设置五分钟有效
                redisTemplate.opsForValue().set(mail,code,5, TimeUnit.MINUTES);
                return "mailLogin/register";
            }
        }
        return "mailLogin/sendMail";
    }

    @PostMapping("/register")
    public String register(@RequestParam String randomNum, HttpServletRequest request){
        // 注册新用户  判断验证码是否过期
        String ss = redisTemplate.opsForValue().get(mail);
        if(StringUtils.hasText(ss)){
            request.setAttribute("msg","验证码已过期！");
            return "mailLogin/sendMail";
        }
        if(!code.equals(randomNum)){
            request.setAttribute("msg","验证码错误！");
            return "mailLogin/register";
        }else{
            MailUser mailuser = new MailUser();
            mailuser.setMail(mail);
            mailuser.setNum(0L);
            mailuser.setVisitTime(new Date());
            mailUserService.addMail(mailuser);
            // 返回注册成功界面
            return "mailLogin/success";
        }
    }
}
