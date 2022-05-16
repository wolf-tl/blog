package com.tl.blog.service;

import com.tl.blog.pojo.QqUser;

import java.util.List;

/**
 * @author tl
 */
public interface QqUserService {
    public List<QqUser> check();
    public void add(QqUser qquser);
}
