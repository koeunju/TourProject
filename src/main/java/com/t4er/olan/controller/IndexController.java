package com.t4er.olan.controller;

import com.t4er.olan.service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;

@Controller
public class IndexController {

    @Autowired
    private SampleService sampleService;

    @RequestMapping("/")
    public String home(Model m) {

        return "index";
    }

    @RequestMapping("/index")
    public String index(Model m) {

        return "index";
    }

    @RequestMapping("/test")
    public String test(Model m) {
        int n = this.sampleService.totalCount();
        m.addAttribute("m", n);
        return  "test";
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
}
