package com.lu.mapper;

import com.lu.po.Type;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface TypeMapper {

    List<Type> findAllType();
    List<Type> findAllTypeAndBlog();

    Integer insertType(Type type);
    Type findById(Long id);
    Integer updateById(Type type);
    Integer deleteById(Long id);
     List<Type> findBlogByTypeName(String name);
}
