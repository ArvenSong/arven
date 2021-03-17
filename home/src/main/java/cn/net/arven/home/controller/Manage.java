package cn.net.arven.home.controller;

import cn.net.arven.common.entity.Tag;
import cn.net.arven.home.service.IFileService;
import cn.net.arven.home.service.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
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
    @ResponseBody
    public Object tagAll() {
        List<Tag> tagList = tagService.getAll();
        Map<String, Object> model = new HashMap<>();
        model.put("code", 0);
        model.put("msg", "");
        model.put("data", tagList);
        model.put("count", tagList.size());
        return model;
    }

    @RequestMapping("/tag/delete")
    @ResponseBody
    public Object tagDelete(@RequestParam("id")String id) {
        return tagService.removeById(id);
    }

    @RequestMapping("/tag/add")
    @ResponseBody
    public Object tagAdd(@RequestParam("name")String name) {
        return tagService.tagAdd(name);
    }

    @RequestMapping("/tag")
    public Object tag() {
        ModelAndView mv = new ModelAndView("tag");
        Map<String, Object> model = mv.getModel();
        model.put("tagList",tagService.getAll());
        return mv;
    }

}
