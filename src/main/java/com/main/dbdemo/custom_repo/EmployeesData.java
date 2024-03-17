package com.main.dbdemo.custom_repo;

import com.main.dbdemo.entities.EmployeeDesignationModel;
import com.main.dbdemo.entities.EmployeesModel;
import org.springframework.http.ResponseEntity;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public interface EmployeesData {

    ResponseEntity<?> checkLoginDetails(Map<String,Object> param);

    List<EmployeesModel> getAllEmployeesList();

    ResponseEntity<?> createEmployeesList(Map<String,Object> param);

    ResponseEntity<?> createDesignationList(Map<String,Object> param);

    List<EmployeeDesignationModel> getAllDesignationList();

    ResponseEntity<?> createDesignationfromDaoFinal(String designationname, Connection connection);
}
