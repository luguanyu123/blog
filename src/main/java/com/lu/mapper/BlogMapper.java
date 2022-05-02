package com.lu.mapper;

import com.lu.vo.BlogList;
import com.lu.po.Blog;
import com.lu.vo.SearchBlog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface BlogMapper {

    List<BlogList> findAllBlogList();
    List<Blog> findAllBlog();
    List<Blog> findPublishedBlog();
    Blog findById(Long id);
    List<Blog> findByType(Long id);
    List<Blog> searchByTitle(String title);
    List<String> findYear();
    List<Blog> findByYear(String year);
    Integer findBlogCount();
    Integer insertBlog(Blog blog);

    Integer updateBlog(Blog blog);
    Integer updateViews(Blog blog);

    Integer deleteById(Long id);


}
