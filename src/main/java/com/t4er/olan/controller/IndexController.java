package com.t4er.olan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("/")
    public String home(Model m) {

        return "index";
    }

    @RequestMapping("/index")
    public String index(Model m) {

        return "index";
    }

    @RequestMapping("/top")
    public void top() {

    }

    @RequestMapping("/foot")
    public void foot(){

    }
    @RequestMapping("/top_sub")
    public void top_sub() {

    }

    @RequestMapping("/foot_sub")
    public void foot_sub(){

    }
    @RequestMapping("/menubar")
    public void menubar(){

    }
}
