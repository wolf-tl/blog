package com.tl.blog.service;


import com.tl.blog.mapper.TagMapper;
import com.tl.blog.pojo.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
/**
 * @author tl
 */
@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagMapper tagMapper;
    @Transactional
    @Override
    public int saveTag(Tag tag) {
        return tagMapper.saveTag(tag);
    }

    @Override
    public Tag getTag(Long id) {
        return tagMapper.getTag(id);
    }

    @Override
    public Tag getTagByName(String name) {
        return tagMapper.getTagByName(name);
    }

    @Override
    public List<Tag> getAllTag() {
        return tagMapper.getAllTag();
    }

    @Override
    public List<Tag> getBlogTag() {
        return tagMapper.getBlogTag();
    }

    @Transactional
    @Override
    public int updateTag(Tag tag) {
        return tagMapper.updateTag(tag);
    }

    @Transactional
    @Override
    public int deleteTag(Long id) {
        return tagMapper.deleteTag(id);
    }

    @Override
    public List<Tag> getTagByString(String text) {    //从tagIds字符串中获取id，根据id获取tag集合
        List<Tag> tags = new ArrayList<>();
        List<Long> longs = convertToList(text);
        for (Long long1 : longs) {
            tags.add(tagMapper.getTag(long1));
        }
        return tags;
    }
    private List<Long> convertToList(String ids) {  //把前端的tagIds字符串转换为list数组
        List<Long> list = new ArrayList<>();
        if (!"".equals(ids) && ids != null) {
            String[] idarray = ids.split(",");
            for (int i=0; i < idarray.length;i++) {
                list.add(new Long(idarray[i]));  //将String型转为Long型
            }
        }
        return list;
    }
}
