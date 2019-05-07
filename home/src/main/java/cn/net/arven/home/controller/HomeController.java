package cn.net.arven.home.controller;

import cn.net.arven.common.constant.Constant;
import cn.net.arven.home.service.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @author 宋建华
 * @date 2019-05-05 10:17
 **/
@Controller
public class HomeController {

    @Autowired
    IFileService fileService;

    @RequestMapping("/")
    public Object home() {
        ModelAndView mv = new ModelAndView("index");
        Map<String, Object> model = mv.getModel();
        model.put("small", Constant.STATIC_SMALL_URL);
        model.put("large", Constant.STATIC_LARGE_URL);
        model.put("truth", Constant.STATIC_TRUTH_URL);
        model.put(Constant.FILE_TAG_BANNER, fileService.getFileByTag(Constant.FILE_TAG_BANNER, Constant.BANNER_SIZE, Constant.TRUE));
        model.put(Constant.FILE_TAG_PHOTOGRAPHER, fileService.getFileByTag(Constant.FILE_TAG_PHOTOGRAPHER, null, null));
        model.put(Constant.FILE_TAG_GALLERY + "_crosswise", fileService.getFileByTag(Constant.FILE_TAG_GALLERY, null, Constant.TRUE));
        model.put(Constant.FILE_TAG_GALLERY + "_lengthwise", fileService.getFileByTag(Constant.FILE_TAG_GALLERY, null, Constant.FALSE));
        return mv;
    }

    @RequestMapping("/index.html")
    public Object index() {
        return home();
    }
}
