package com.tl.blog.service;

import com.tl.blog.pojo.Tag;

import java.util.List;
/**
 * @author tl
 */
public interface TagService {

        int saveTag(Tag tag);

        Tag getTag(Long id);

        Tag getTagByName(String name);

        List<Tag> getAllTag();

        List<Tag> getBlogTag();  //首页展示博客标签

        List<Tag> getTagByString(String text);   //从字符串中获取tag集合

        int updateTag(Tag tag);

        int deleteTag(Long id);

}
