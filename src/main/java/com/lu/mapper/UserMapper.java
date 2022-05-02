package com.lu.mapper;

import com.lu.po.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User findByUsernameAndPassword(User user);

    User findByUsername(String name);
}
