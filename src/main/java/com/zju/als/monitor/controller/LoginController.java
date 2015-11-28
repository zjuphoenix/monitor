package com.zju.als.monitor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by MCH on 2015/11/23.
 */

@Controller
@RequestMapping("/login")
public class LoginController {
    @RequestMapping()
    public ModelAndView login(){
        return new ModelAndView("login");
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public boolean auth(){
        return true;
    }
}
