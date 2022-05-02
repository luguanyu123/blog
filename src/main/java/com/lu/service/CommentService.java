package com.lu.service;

import com.lu.po.Comment;

import java.util.List;

public interface CommentService {

    Integer addComment(Comment comment);

    List<Comment> findByBlogId(Long blogId);
    List<Comment> findParentComments(Long blogId);

    List<Comment> findListByBlogId(Long blogId);






}
