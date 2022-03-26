package com.tl.blog.mapper;

import com.tl.blog.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author tl
 */
@Mapper
@Repository
public interface UserMapper {
    public User checkUser(@Param("username") String username, @Param("password") String password);
}
