package com.main.dbdemo.controllers;

import com.main.dbdemo.services.StudentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = "/api/student")
public class StudentController {

    @Autowired
    public StudentServices studentServices;

    @PostMapping("/create")
    public Object createStudent(@RequestBody Map<String,String> param) throws Exception {

        Map<String,Map<String,Object>> create_student = studentServices.createStudent(param);

        return create_student;
    }

    @GetMapping("/getdetails")
    public List<Map<Long,Object>> getDetails(@RequestParam Map<String,String> param) throws Exception{

        List<Map<Long,Object>> get_details = studentServices.getDetails(param);
        return get_details;
    }
}
