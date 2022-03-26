package com.tl.blog.service;

import com.tl.blog.mapper.TypeMapper;
import com.tl.blog.pojo.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * @author tl
 */
@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeMapper typeMapper;
    @Transactional
    @Override
    public int saveType(Type type) {
        return typeMapper.saveType(type);
    }

    @Override
    public Type getType(Long id) {
        return typeMapper.getType(id);
    }

    @Override
    public Type getTypeByName(String name) {
        return typeMapper.getTypeByName(name);
    }

    @Override
    public List<Type> getAllType() {
        return typeMapper.getAllType();
    }

    @Override
    public List<Type> getBlogType() {
        return typeMapper.getBlogType();
    }
    @Transactional
    @Override
    public int updateType(Type type) {
        return typeMapper.updateType(type);
    }
    @Transactional
    @Override
    public int deleteType(Long id) {
        return typeMapper.deleteType(id);
    }
}
