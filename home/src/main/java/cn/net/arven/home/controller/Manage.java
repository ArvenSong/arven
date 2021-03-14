package cn.net.arven.home.controller;

import cn.net.arven.home.service.IFileService;
import cn.net.arven.home.service.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/manage")
public class Manage {


    @Autowired
    IFileService fileService;

    @Autowired
    ITagService tagService;

    @RequestMapping("/home.html")
    public Object index() {
        ModelAndView mv = new ModelAndView("home");

        return mv;
    }

    @RequestMapping("/tag/all")
    public Object tagAll() {
        ModelAndView mv = new ModelAndView("tagList");
        Map<String, Object> model = mv.getModel();
        model.put("tagList" ,tagService.getAll());
        return mv;
    }

}
