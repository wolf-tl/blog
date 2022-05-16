package com.tl.blog.service;

import com.tl.blog.mapper.QqUserMapper;
import com.tl.blog.pojo.QqUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author tl
 */
@Service
@Transactional
public class QqUserServiceImpl implements QqUserService {

    @Autowired
    private QqUserMapper qqUserMapper;

    @Override
    public List<QqUser> check() {
        List<QqUser> list = qqUserMapper.check();
        return list;
    }

    @Override
    public void add(QqUser qquser) {
        qqUserMapper.add(qquser);
    }
}
