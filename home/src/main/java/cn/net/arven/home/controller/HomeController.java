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
        model.put("breviary", Constant.FILE_SERVICE_URL_BREVIARY);
        model.put("bannerUrl", Constant.FILE_SERVICE_URL_BANNER);
        model.put("truth", Constant.FILE_SERVICE_URL_TRUTH);
        model.put(Constant.FILE_TAG_BANNER, fileService.getFileByTag(Constant.FILE_TAG_BANNER));
        model.put(Constant.FILE_TAG_PHOTOGRAPHER, fileService.getFileByTag(Constant.FILE_TAG_PHOTOGRAPHER));
        return mv;
    }

    @RequestMapping("/index.html")
    public Object index() {
        return home();
    }
}
