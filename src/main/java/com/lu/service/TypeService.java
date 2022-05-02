package com.lu.service;

import com.lu.po.Type;

import java.util.List;

public interface TypeService {

    List<Type> listType(Integer pageNum, Integer pageSize);
    Integer addType(Type type);
    Type findById(Long id);
    Integer editById(Type type);
    Integer deleteById(Long id);
    List<Type> findAllTypes();
    List<Type> findBlogByTypeName(String name);

}
