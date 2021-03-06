package com.app.controllersWeb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class MainController {

    @GetMapping("/")
    public String welcome() {
        return "index";
    }

    @GetMapping("/cookies")
    public String cookies() {
        return "policies/cookies";
    }

    @GetMapping("/rodo")
    public String rodo() {
        return "policies/rodo";
    }
}