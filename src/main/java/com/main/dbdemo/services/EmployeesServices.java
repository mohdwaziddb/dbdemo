package com.main.dbdemo.services;

import com.main.dbdemo.entities.EmployeesModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public interface EmployeesServices {

    ResponseEntity<?>  checkLogin(Map<String,Object> param) throws Exception;

    Map<String, Object> getEmployeeData(Map<String,Object> param) throws Exception;

    ResponseEntity<?> createEmployee(Map<String,Object> param) throws Exception;

    Map<String,Object> employeeListbyname(Map<String,Object> param) throws Exception;

    Map<String, Object> deleteEmployeebyID(Map<String,Object> param) throws Exception;

    ResponseEntity<?> createDesignation(Map<String,Object> param) throws Exception;

    Map<String, Object> getDesignationData(Map<String,Object> param) throws Exception;

    List<EmployeesModel> emplist1()throws Exception;

    ResponseEntity<?> createDesignationfromDao(String designationname, Connection connection);


}
