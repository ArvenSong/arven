package cn.net.arven.home.controller;

import cn.net.arven.common.constant.Constant;
import cn.net.arven.common.entity.File;
import cn.net.arven.common.entity.Tag;
import cn.net.arven.home.service.IFileService;
import cn.net.arven.home.service.ITagService;
import cn.net.arven.home.vo.FileVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
    @RequestMapping("/tag/modify")
    @ResponseBody
    public Object tagModify(@RequestParam("id")String id,@RequestParam("name")String name) {
        return tagService.tagModify(id,name);
    }

    @RequestMapping("/tag")
    public Object tag() {
        ModelAndView mv = new ModelAndView("tag");
        Map<String, Object> model = mv.getModel();
        model.put("tagList",tagService.getAll());
        return mv;
    }
    @RequestMapping("/imageList")
    public Object imageList() {
        ModelAndView mv = new ModelAndView("imageList");
        Map<String, Object> model = mv.getModel();
        model.put("tagList",tagService.getAll());
        return mv;
    }
    @RequestMapping("/imageList/page")
    @ResponseBody
    public Object pageList(@RequestParam("page")Long page,@RequestParam("limit")Long limit
                        ,@RequestParam(value = "tagId",required = false)String tagId,
                           @RequestParam(value = "name",required = false)String name) {
        Map<String, Object> result = new HashMap<>();
        IPage<FileVO> fileVOPage = fileService.getAll(page, limit,tagId,name);
        result.put("data", fileVOPage.getRecords());
        result.put("count",fileVOPage.getTotal());
        result.put("msg","");
        result.put("code",0);
        return result;
    }
    @RequestMapping("/imageView/{id}")
    public Object imageView(@PathVariable("id") String id) {
        ModelAndView mv = new ModelAndView("imageView");
        Map<String, Object> model = mv.getModel();
        model.put("image",fileService.showOne(id));
        model.put("large", Constant.STATIC_LARGE_URL);
        return mv;
    }

    @RequestMapping("/imageEdit/{id}")
    public Object imageEdit(@PathVariable("id") String id) {
        ModelAndView mv = new ModelAndView("imageEdit");
        Map<String, Object> model = mv.getModel();
        model.put("image",fileService.getById(id));
        model.put("large", Constant.STATIC_LARGE_URL);
        model.put("tagList",tagService.getAll());
        return mv;
    }
    @RequestMapping("/imageUpdate")
    @ResponseBody
    public Object imageUpdate(File file) {
        return fileService.updateById(file);
    }

    @RequestMapping("/imageDel/{id}")
    @ResponseBody
    public Object imageDel(@PathVariable("id") String id) {
        return fileService.removeById(id);
    }
}
