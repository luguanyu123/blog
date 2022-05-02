package com.lu.web;

import com.lu.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ArchivesController {

    @Autowired
    BlogService blogService;

    @GetMapping("/archives")
    public String achives(Model model){
        model.addAttribute("archiveMap", blogService.findArchives());
        model.addAttribute("blogCount", blogService.findBlogCount());
        return "archives";
    }
}
