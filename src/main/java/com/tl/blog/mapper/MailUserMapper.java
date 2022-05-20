package com.tl.blog.mapper;

import com.tl.blog.pojo.MailUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author tl
 */
@Mapper
@Repository
public interface MailUserMapper {
    MailUser checkout (String mail);
    List<MailUser> checkoutAll ();
    void addMail(MailUser mailUser);
    void update(MailUser mailUser);
}
