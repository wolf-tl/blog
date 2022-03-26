package com.tl.blog.service;

import com.tl.blog.mapper.UserMapper;
import com.tl.blog.pojo.User;
import com.tl.blog.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author tl
 */
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;
    @Override
    //此处的md5加密是属于后端加密，避免数据库储存明文密码；
    public User checkUser(String username, String password) {
        User user = userMapper.checkUser(username, MD5Utils.code(password));
        return user;
    }
}
