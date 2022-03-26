package com.tl.blog.service;



import com.tl.blog.pojo.Message;

import java.util.List;

/**
 * @author tl
 */
public interface MessageService {

    //查询留言列表
    List<Message> listMessage();

    //保存留言
    int saveMessage(Message message);

    //删除留言
    void deleteMessage(Long id);

}