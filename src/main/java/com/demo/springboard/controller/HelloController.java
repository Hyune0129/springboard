package com.demo.springboard.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("/hello")
    String hello(Model model) {
        model.addAttribute("date", new Date());
        return "hello";
    }
}
