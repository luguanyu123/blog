package com.lu.mapper;

import com.lu.po.Tag;

import java.util.List;

public interface TagMapper {

    List<Tag> findAllTag();
    Integer insertTag(Tag tag);
    Tag findById(Long id);
    Integer updateById(Tag tag);
    Integer deleteById(Long id);

}
