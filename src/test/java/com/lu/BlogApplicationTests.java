package com.lu;

import com.lu.mapper.BlogMapper;
import com.lu.po.Blog;
import com.lu.service.BlogService;
import com.lu.service.CommentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
class BlogApplicationTests {
    @Autowired
    BlogService blogService;
    @Autowired
    BlogMapper blogMapper;
    @Autowired
    CommentService commentService;

    @Test
    void contextLoads() {
        List<String> years = blogMapper.findYear();
        Map<String, List<Blog>> map = new LinkedHashMap<>();
        for (String year : years) {
            map.put(year, blogMapper.findByYear(year));
        }
        System.out.println(map);

    }

}
