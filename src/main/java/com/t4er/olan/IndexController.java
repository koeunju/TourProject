package com.t4er.olan;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("/index")
    public String index(Model m) {
    	
    	m.addAttribute("test", "testSuccess");

        return "index";
    }

    @RequestMapping("/top")
    public void top() {

    }

    @RequestMapping("/foot")
    public void foot(){

    }
}
