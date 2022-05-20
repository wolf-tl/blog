package com.tl.blog.service;

import com.tl.blog.pojo.MailUser;

import java.util.List;

/**
 * @author tl
 */
public interface MailUserService {
    MailUser checkout(String mail);
    List<MailUser> checkoutAll ();
    void addMail(MailUser user);
    void update(MailUser mailUser);
}
