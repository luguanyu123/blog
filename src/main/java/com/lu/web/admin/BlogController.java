package com.lu.web.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lu.po.Blog;
import com.lu.po.Tag;
import com.lu.po.Type;
import com.lu.po.User;
import com.lu.service.BlogService;
import com.lu.service.TagService;
import com.lu.service.TypeService;
import com.lu.vo.BlogList;
import com.lu.vo.SearchBlog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    BlogService blogService;
    @Autowired
    TypeService typeService;
    @Autowired
    TagService tagService;


    @GetMapping("/blogs")
    public String blogsPage(@RequestParam(defaultValue="1",value="pageNum") Integer pageNum,
                            Model model){

        PageHelper.startPage(pageNum, 4);
        List<BlogList> blogList = blogService.findAllBlogList();
        List<Type> types=typeService.findAllTypes();
        PageInfo<BlogList> pageInfo = new PageInfo<>(blogList);
        System.out.println(pageInfo);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("types",types);

        return "admin/blogs";
    }
    //根据标题查询
    @PostMapping("/blogs/search")
    public String searchBlogs(String title,Long id,@RequestParam(required = false,defaultValue ="1",
    value = "page")int pagdNum,Model model){
        PageHelper.startPage(pagdNum,4);
        List<Blog> blogs = blogService.searchByTitle(title);
        //List<Blog> types = blogService.findByType(id);
        PageInfo pageInfo = new PageInfo(blogs);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("message","查询成功");
        //model.addAttribute("types", types);
        return "admin/blogs::blogList";
    }



    @GetMapping("/blogs/input")
    public String inputPage(Model model){

        List<Type> types = typeService.findAllTypes();
        List<Tag> tags = tagService.findAllTags();
        model.addAttribute("blog", new Blog());
        model.addAttribute("types", types);
        model.addAttribute("tags",tags);
        return "admin/blogs-input";
    }

    @GetMapping("/blogs/update")
    public String updatePage(Long id, Model model){

        Blog blog = blogService.findById(id);
        List<Type> types = typeService.findAllTypes();
        List<Tag> tags = tagService.findAllTags();
        model.addAttribute("blog", blog);
        model.addAttribute("types", types);
        model.addAttribute("tags",tags);
        System.out.println(blog);
        return "admin/blogs-input";
    }


    @PostMapping("/blogs/input")
    public String input(Blog blog, Model model,
                        HttpSession session, RedirectAttributes attributes){
        User user = (User) session.getAttribute("user");
        blog.setUser(user);
        blog.setViews(0);
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        Integer i = blogService.addBlog(blog);
        if (i>0){
            attributes.addFlashAttribute("message", "添加博客成功");
        } else {
            attributes.addFlashAttribute("message", "添加博客失败");
        }
        return "redirect:/admin/blogs";
    }

    @PostMapping("/blogs/update")
    public String update(Blog blog, Model model, RedirectAttributes attributes){

        blog.setUpdateTime(new Date());
        Integer i = blogService.updateById(blog);
        if (i>0){
            attributes.addFlashAttribute("message", "更新博客成功");
        } else {
            attributes.addFlashAttribute("message", "更新博客失败");
        }
        return "redirect:/admin/blogs";
    }

    @GetMapping("/blogs/delete")
    public String delete(Long id, Model model, RedirectAttributes attributes){

        Integer i = blogService.deleteById(id);
        if (i>0){
            attributes.addFlashAttribute("message", "删除博客成功");
        } else {
            attributes.addFlashAttribute("message", "删除博客失败");
        }
        return "redirect:/admin/blogs";
    }

}
