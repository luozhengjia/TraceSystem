package com.ejunhai.trace.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class HomeController {

    @RequestMapping("/qcode")
    public String qcode(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws IOException {
        return "index";
    }
}
