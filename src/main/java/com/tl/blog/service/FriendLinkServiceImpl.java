package com.tl.blog.service;


import com.tl.blog.mapper.FriendLinkMapper;
import com.tl.blog.pojo.FriendLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * @author tl
 */
@Service
public class FriendLinkServiceImpl implements FriendLinkService {

    @Autowired
    private FriendLinkMapper friendLinkMapper;

    @Override
    public List<FriendLink> listFriendLink() {
        return friendLinkMapper.listFriendLink();
    }

    @Transactional
    @Override
    public int saveFriendLink(FriendLink friendLink) {
        return friendLinkMapper.saveFriendLink(friendLink);
    }

    @Override
    public FriendLink getFriendLink(Long id) {
        return friendLinkMapper.getFriendLink(id);
    }

    @Override
    public FriendLink getFriendLinkByBlogaddress(String blogaddress) {
        return friendLinkMapper.getFriendLinkByBlogaddress(blogaddress);
    }

    @Transactional
    @Override
    public int updateFriendLink(FriendLink friendLink) {
        return friendLinkMapper.updateFriendLink(friendLink);
    }

    @Transactional
    @Override
    public void deleteFriendLink(Long id) {
        friendLinkMapper.deleteFriendLink(id);
    }


}