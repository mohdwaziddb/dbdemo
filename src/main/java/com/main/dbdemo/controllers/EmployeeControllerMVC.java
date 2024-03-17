package com.main.dbdemo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/mvc/dashboard")
public class EmployeeControllerMVC {

    @RequestMapping(value = "/employee", method = {RequestMethod.GET,RequestMethod.POST})
    public Object employees(){
        return "emp_dashboard/employee";
    }

    @RequestMapping(value = "/createdesignation", method = {RequestMethod.GET,RequestMethod.POST})
    public String createDesignation(){
        return "emp_dashboard/createdesignation";
    }

    @RequestMapping(value = "/employeedetail", method = {RequestMethod.GET,RequestMethod.POST})
    public String employeeDetail(){
        return "emp_dashboard/employeedetail";
    }

    @RequestMapping(value = "/createaccount", method = {RequestMethod.GET,RequestMethod.POST})
    public String createAccount(){
        return "emp_dashboard/createaccount";
    }
}
