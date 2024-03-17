package com.main.dbdemo.services;

import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface StudentServices {

    Map<String,Map<String,Object>> createStudent(Map<String,String> param) throws Exception;

    List<Map<Long,Object>> getDetails(Map<String,String> param) throws Exception;


}
