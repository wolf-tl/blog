package com.tl.blog.mapper;

import com.tl.blog.pojo.MailUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author tl
 */
@Mapper
@Repository
public interface MailUserMapper {
    public MailUser checkout (String mail);
    public void addMail(MailUser mailUser);
}
