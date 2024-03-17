package com.main.dbdemo.controllers;

import com.main.dbdemo.database.MySqlDatabase;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;


@Controller
public class IndexController {
//    static {
//        try {
//            MySqlDatabase.connect();
//            System.out.println("connection established");
//        } catch (Exception e){
//            System.out.println(e);
//        }
//
//    }

    @RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.POST})
    public String index(HttpServletRequest req, ModelMap modelMap) {
        return "emp_dashboard/login";
    }
}
