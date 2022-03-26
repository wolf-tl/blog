package com.tl.blog.service;

import com.tl.blog.pojo.Type;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * @author tl
 */
public interface TypeService {


    int saveType(Type type);

    Type getType(Long id);

    Type getTypeByName(String name);

    List<Type> getAllType();

    List<Type> getBlogType();  //首页右侧展示type对应的博客数量

    int updateType(Type type);

    int deleteType(Long id);
}
