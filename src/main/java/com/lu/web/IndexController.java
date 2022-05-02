package com.lu.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lu.po.Blog;
import com.lu.po.Comment;
import com.lu.po.Type;
import com.lu.service.BlogService;
import com.lu.service.CommentService;
import com.lu.service.TagService;
import com.lu.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    BlogService blogService;
    @Autowired
    TypeService typeService;
    @Autowired
    TagService tagService;
    @Autowired
    CommentService commentService;

    @GetMapping("/")
    public String indexPage(@RequestParam(defaultValue="1",value="pageNum") Integer pageNum,
                            Model model){
        //查询全部博客、分类、推荐...并传递到前台
        PageHelper.startPage(pageNum, 4);
        List<Blog> blogs = blogService.findPublishedBlog();
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);

        List<Type> types = typeService.findAllTypes();

        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("types", types);
        return "index";
    }

    @GetMapping("/blog")
    public String blogPage(Long id, Model model){
        List<Comment> comments = commentService.findListByBlogId(id);
        Blog blog = blogService.blogDetails(id);
        blog.setViews(blog.getViews()+1);
        Integer i = blogService.updateViews(blog);
        model.addAttribute("blog", blog);
        model.addAttribute("comments", comments);
        return "blog";
    }

    @GetMapping("/search")
    public String search(@RequestParam(defaultValue="1",value="pageNum") Integer pageNum,
                         String query, Model model){
        PageHelper.startPage(pageNum, 2);
        List<Blog> blogs = blogService.searchByTitle(query);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("query", query);
        return "search";
    }

}
