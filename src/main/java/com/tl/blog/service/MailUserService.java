package com.tl.blog.service;

import com.tl.blog.pojo.MailUser;

/**
 * @author tl
 */
public interface MailUserService {
    public MailUser checkout(String mail);
    public void addMail(MailUser user);
}
