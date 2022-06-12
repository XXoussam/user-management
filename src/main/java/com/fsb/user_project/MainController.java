package com.fsb.user_project;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.GeneratedValue;

@Controller
public class MainController {
    @GetMapping("")
    public String showHomePage() {
        return "index";
    }
}
