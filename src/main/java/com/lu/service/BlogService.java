package com.lu.service;

import com.lu.po.Type;
import com.lu.vo.BlogList;
import com.lu.po.Blog;
import com.lu.vo.SearchBlog;

import java.util.List;
import java.util.Map;

public interface BlogService {

    List<BlogList> findAllBlogList();
    List<Blog> findAllBlog();
    List<Blog> findPublishedBlog();
    List<Blog> findByType(Long id);
    List<Blog> searchByTitle(String title);
    Map<String, List<Blog>> findArchives();
    Integer findBlogCount();
    Integer addBlog(Blog blog);

    Blog findById(Long id);
    Blog blogDetails(Long id);

    Integer updateById(Blog blog);
    Integer updateViews(Blog blog);

    Integer deleteById(Long id);

}
