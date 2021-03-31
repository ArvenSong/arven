package cn.net.arven.home.controller;

import cn.net.arven.common.constant.Constant;
import cn.net.arven.home.service.IFileService;
import cn.net.arven.home.service.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 宋建华
 * @date 2019-05-05 10:17
 **/
@Controller
public class HomeController {

    @Autowired
    IFileService fileService;

    @Autowired
    ITagService tagService;

    @RequestMapping("/")
    public Object home() {
        ModelAndView mv = new ModelAndView("index");
        Map<String, Object> model = mv.getModel();
        model.put("small", Constant.STATIC_SMALL_URL);
        model.put("large", Constant.STATIC_LARGE_URL);
        model.put("truth", Constant.STATIC_TRUTH_URL);
        model.put("tagList" ,tagService.getAll());
        List<String> banner = new ArrayList<>();
        banner.add(Constant.FILE_TAG_BANNER);
        List<String> photographer = new ArrayList<>();
        photographer.add(Constant.FILE_TAG_PHOTOGRAPHER);
        List<String> gallery = new ArrayList<>();
        gallery.add(Constant.FILE_TAG_GALLERY);
        model.put(Constant.FILE_TAG_BANNER, fileService.getFileByTag(banner, Constant.BANNER_SIZE, Constant.TRUE));
        model.put(Constant.FILE_TAG_PHOTOGRAPHER, fileService.getFileByTag(photographer, null, null));
        model.put(Constant.FILE_TAG_GALLERY + "_crosswise", fileService.getFileByTag(gallery, null, Constant.TRUE));
        model.put(Constant.FILE_TAG_GALLERY + "_lengthwise", fileService.getFileByTag(gallery, null, Constant.FALSE));
        return mv;
    }

    @RequestMapping("/index.html")
    public Object index() {
        return home();
    }

    @RequestMapping("/login.html")
    public Object login() {
        return new ModelAndView("login");
    }
    @RequestMapping("/login")
    public Object signIn(@RequestParam("username") String username,
                         @RequestParam("password") String password,
                        HttpServletRequest request) {
        if (Constant.USERNAME.equals(username) && Constant.PASSWORD.equals(password)) {
            request.getSession().setAttribute("username",username);
            request.getSession().setAttribute("password",password);
            return "redirect:/manage/home.html";
        }
        return new ModelAndView("login");
    }

    @RequestMapping("/galleryData")
    public Object galleryData(@RequestParam(value = "tag[]",required = false) List<String> tag, Model model) {
        model.addAttribute("small", Constant.STATIC_SMALL_URL);
        model.addAttribute("large", Constant.STATIC_LARGE_URL);
        model.addAttribute("truth", Constant.STATIC_TRUTH_URL);
        model.addAttribute(Constant.FILE_TAG_GALLERY + "_crosswise", fileService.getFileByTag(tag, null, Constant.TRUE));
        model.addAttribute(Constant.FILE_TAG_GALLERY + "_lengthwise", fileService.getFileByTag(tag, null, Constant.FALSE));
        System.err.println(tag);
        return "index::photoDataDiv";
    }
}
