package com.tl.blog.mapper;

import com.tl.blog.pojo.QqUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author tl
 */
@Mapper
@Repository
public interface QqUserMapper {
    public List<QqUser> check();
    public void add(QqUser qquser);
}
