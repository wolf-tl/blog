package com.tl.blog.controller.userLogin;

import com.alibaba.fastjson.JSONObject;
import com.tl.blog.interceptor.QqHttpClient;
import com.tl.blog.pojo.QqUser;
import com.tl.blog.service.QqUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.UUID;

/**
 * @author tl
 */

@Controller
@RequestMapping(value = "/qqlogin")
public class QqLoginController {

    /**
     * QQ互联中提供的 appid 和 appkey
     */
    @Value("${qq.oauth.appid}")
    public String APPID;
    @Value("${qq.oauth.appkey}")
    public String APPKEY;
    @Value("${qq.oauth.url}")
    public String URL;

    @Autowired
    private QqUserService qqUserService;

    @GetMapping()
    public String loginPage(){
        return "qqLogin/login";
    }

    /**
     * 请求授权页面
     */
    @GetMapping(value = "/auth")
    public void qqAuth(HttpSession session, HttpServletResponse response) throws IOException {
        // 用于第三方应用防止CSRF攻击
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        session.setAttribute("state", uuid);

        // Step1：获取Authorization Code
        String url = "https://graph.qq.com/oauth2.0/authorize?response_type=code" +
                "&client_id=" + APPID +
                "&redirect_uri=" + URLEncoder.encode(URL) +
                "&state=" + uuid;

        response.sendRedirect(url);
    }

    /**
     * 授权回调
     */
    @PostMapping(value = "/callback")
    public String qqCallback(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        // 得到Authorization Code
        String code = request.getParameter("code");
        // 我们放在地址中的状态码
        String state = request.getParameter("state");
        // 验证信息
        String uuid = (String) session.getAttribute("state");

        // 验证信息我们发送的状态码
        if (null != uuid) {
            // 状态码不正确，直接返回登录页面
            if (!uuid.equals(state)) {
                response.sendRedirect("/qqlogin");
            }
        }

        // Step2：通过Authorization Code获取Access Token
        String url = "https://graph.qq.com/oauth2.0/token?grant_type=authorization_code" +
                "&client_id=" + APPID +
                "&client_secret=" + APPKEY +
                "&code=" + code +
                "&redirect_uri=" + URL;
        String access_token = QqHttpClient.getAccessToken(url);

        // Step3: 获取回调后的openID
        url = "https://graph.qq.com/oauth2.0/me?access_token=" + access_token;
        String openId = QqHttpClient.getOpenID(url);

        // Step4：获取QQ用户信息
        url = "https://graph.qq.com/user/get_user_info?access_token=" + access_token +
                "&oauth_consumer_key=" + APPID +
                "&openid=" + openId;

        // 得到用户信息
        JSONObject jsonObject = QqHttpClient.getUserInfo(url);
        //获取用户昵称
        String nickname = jsonObject.getString("nickname");
        //qq头像地址
        String avatar = jsonObject.getString("figureurl_qq_1");
        //获取性别
        String gender = jsonObject.getString("gender");
        //将用户储存数据库
        QqUser qqUser = new QqUser();
        qqUser.setOpenId(openId);
        qqUser.setNickname(nickname);
        qqUser.setQqAvatar(avatar);
        qqUser.setGender(gender);
        qqUser.setCreateTime(new Date());

        qqUserService.add(qqUser);
        //传给session json对象
        session.setAttribute("qquser",jsonObject);
        return "redirect:/";
    }
}
