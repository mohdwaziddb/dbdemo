package com.main.dbdemo.controllers;


import com.main.dbdemo.custom_repo.SQLQuery;
import com.main.dbdemo.database.MySqlDatabase;
import com.main.dbdemo.entities.EmployeesModel;
import com.main.dbdemo.reports.EmployeesReports;
import com.main.dbdemo.services.EmployeesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.util.*;

@RestController
@RequestMapping(value = "/api/testing")
public class EmployeeController {

    @Autowired
    public EmployeesServices employeesServices;

    @Autowired
    EmployeesReports employeesReports;

    @Autowired
    SQLQuery sqlQuery;

    static Connection connection = null;
    static {
        try {
            connection = MySqlDatabase.connect();
            System.out.println("connection established");
        } catch (Exception e){
            System.out.println(e);
        }
    }

    @PostMapping("/login")
    public Object checkLogin(@RequestBody Map<String,Object> param){
        try{
            return employeesServices.checkLogin(param);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Wrong login information", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/EmployeeList")
    public Object getEmployeeList(@RequestParam Map<String,Object> param){
        try{
            return employeesServices.getEmployeeData(param);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("An error occurred while processing the request.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/CreateEmployee")
    public ResponseEntity<?> createEmployee(@RequestBody Map<String,Object> param){
        try{
            return employeesServices.createEmployee(param);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("An error occurred while processing the request.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getEmployeeListbyname")
    public Object employeeListbyname(@RequestParam Map<String,Object> param){
        try{
            return employeesServices.employeeListbyname(param);
        } catch (Exception e){
            e.printStackTrace();
            return "Error";
        }
    }

    @DeleteMapping("/deleteEmployeebyID")
    public Object deleteEmployeebyID(@RequestParam Map<String,Object> param){
        try{
            return employeesServices.deleteEmployeebyID(param);
        } catch (Exception e){
            e.printStackTrace();
            return "Error";
        }
    }

    @PostMapping("/CreateDesignation")
    public ResponseEntity<?> createDesignation(@RequestBody Map<String,Object> param){
        try{
            return employeesServices.createDesignation(param);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("An error occurred while processing the request.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getEmpDesignationList")
    public Object empDesignationList (@RequestParam Map<String,Object> param){
        try{
            return employeesServices.getDesignationData(param);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("An error occurred while processing the request.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/downloadExcel")
    public void DownloadExcel (HttpServletRequest req, HttpServletResponse resp){
        try{
            employeesReports.employeeListInExcel(req,resp);
        } catch (Exception e){
           new RuntimeException(e);
        }
    }

    @GetMapping("/downloadPDF")
    public void DownloadPDF (HttpServletRequest req, HttpServletResponse resp){
        try{
            employeesReports.employeeListInPDF(req,resp);
        } catch (Exception e){
            new RuntimeException(e);
        }
    }

    @GetMapping("/empliststream")
    public Object getemplistStream (HttpServletRequest req, HttpServletResponse resp){
        try{
            return employeesServices.emplist1();
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("An error occurred while processing the request.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/empDesignationlistSQL")
    public Object getempDesignationlistAll (HttpServletRequest req, HttpServletResponse resp){
        try{
            return sqlQuery.getEmployeebyDesignationALl(connection);

        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("An error occurred while processing the request.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/createDesignationfromDaoController")
    public ResponseEntity<?> createDesignationfromDao(@RequestBody String designationname){

        try{
            return employeesServices.createDesignationfromDao(designationname,connection);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("An error occurred while processing the request.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        //return null;

    }



}
