package com.edu.zju.lab508.artificialliver.monitor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Administrator on 2015/11/3.
 */
@Controller
public class IndexController {
    @RequestMapping("/index")
    public ModelAndView index(){
        return new ModelAndView("index");
    }
}
