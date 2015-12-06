package com.zju.als.monitor.controller;

import com.zju.als.monitor.reporter.Reporter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2015/11/3.
 */
@Controller
public class IndexController {

    @RequestMapping("/index")//http://localhost:8888/index?surgery_no=123
    public ModelAndView index(@RequestParam("surgery_no") String surgery_no){
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("surgery_no", surgery_no);
        return mv;
    }

}
