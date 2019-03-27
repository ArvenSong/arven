package cn.net.arven.tally.controller;

import cn.net.arven.tally.common.util.PageUtils;
import cn.net.arven.tally.entity.Note;
import cn.net.arven.tally.service.INoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author 宋建华
 * @date 2018-12-11 20:15
 **/
//@RestController
@Controller
@RequestMapping("/note")
public class NoteController {
    @Autowired
    private INoteService iNoteService;

    @RequestMapping("/getAll")
    @ResponseBody
    public List<Note> getNotes(){

        return iNoteService.getAll();
    }

    @RequestMapping("/save")
    @ResponseBody
    public Object save(Note note){

        return iNoteService.saveNote(note)>0?"success":"fail";
    }
    @RequestMapping("/list")
    public ModelAndView list(@RequestParam(value = "pageMap", required = false) PageUtils pageUtils) {
        if(pageUtils==null) {
            pageUtils = PageUtils.getInstance();
        }
        PageUtils page = iNoteService.getPage(pageUtils);
        ModelAndView modelAndView = new ModelAndView("list");
        modelAndView.getModel().put("pageUtils", page);
        return modelAndView;
    }

    @RequestMapping("/getPageData")
    @ResponseBody
    public Object getPageData(Integer page,Integer limit) {
        PageUtils pageUtils = iNoteService.getPage(new PageUtils(page,limit));
        return pageUtils;
    }

    @RequestMapping("/getAddUrl")
    @ResponseBody
    public Object getAddUrl(String password) {
        if("123".equals(password)){
            return "/addNoteData";
        }
        return false;
    }

    @RequestMapping("/addNoteData")
    public Object addNoteData() {

        return "add";
    }
    @RequestMapping("/")
    public Object home(){
        return "index";
    }

}
