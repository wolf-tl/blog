package com.tl.blog.service;

import com.tl.blog.mapper.MailUserMapper;
import com.tl.blog.pojo.MailUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author tl
 */
@Service
@Transactional
public class MailUserServiceImpl implements MailUserService{
    @Autowired
    private MailUserMapper mailUserMapper;
    @Override
    public MailUser checkout(String mail) {
        return mailUserMapper.checkout(mail);
    }

    @Override
    public void addMail(MailUser user) {
        mailUserMapper.addMail(user);
    }
}
