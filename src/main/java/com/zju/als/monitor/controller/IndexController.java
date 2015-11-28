package com.zju.als.monitor.controller;

import com.zju.als.monitor.reporter.Reporter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2015/11/3.
 */
@Controller
public class IndexController {

    @RequestMapping("/index")//http://localhost:8888/index
    public ModelAndView index(){
        return new ModelAndView("index");
    }

}
