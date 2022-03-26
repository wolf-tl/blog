package com.tl.blog.service;

import com.tl.blog.pojo.User;


/**
 * @author tl
 */

public interface UserService {
    User checkUser(String username, String password);
}
