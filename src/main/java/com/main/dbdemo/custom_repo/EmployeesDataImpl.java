package com.main.dbdemo.custom_repo;

import com.main.dbdemo.dao.EmployeeDesignationDao;
import com.main.dbdemo.entities.EmployeeDesignationModel;
import com.main.dbdemo.entities.EmployeesModel;
import com.main.dbdemo.repository.EmployeeDesignationRepository;
import com.main.dbdemo.repository.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeesDataImpl implements EmployeesData {

    @Autowired
    EmployeesRepository employeesRepository;

    @Autowired
    EmployeeDesignationRepository employeeDesignationRepository;

    public ResponseEntity<?> checkLoginDetails(Map<String,Object> param) {

        String username = (String) param.get("username");
        String password = (String) param.get("password");
        EmployeesModel employeesModel = employeesRepository.checkLoginCredential(username, password);
        Map<String,Object> map1 = new HashMap<>();

        if(employeesModel != null){
            map1.put("success",true);
            map1.put("message","success");
        } else {
            map1.put("success",false);
            map1.put("message","Check login credentials");
        }

        return new ResponseEntity<>(map1, HttpStatus.OK);

    }

    public List<EmployeesModel> getAllEmployeesList() {

        List<EmployeesModel> list1 = employeesRepository.findAll();
        return list1;
    }

    public ResponseEntity<?> createEmployeesList(Map<String,Object> param) {

        EmployeesModel employeesModal = new EmployeesModel();
        String emp_name = (String) param.get("username");
        String emp_email = (String) param.get("emailid");
        String emp_phoneno = (String) param.get("phoneno");
        String password = (String) param.get("password");
        Long designation_id = (Long) param.get("designation_id");
        employeesModal.setEmpname(emp_name);
        employeesModal.setEmpemail(emp_email);
        employeesModal.setEmpphoneno(emp_phoneno);
        employeesModal.setPassword(password);
        employeesModal.setDesignationid(designation_id);
        employeesRepository.save(employeesModal);

        HashMap<String, Object> res = new HashMap<>();
        res.put("success", true);
        res.put("data", employeesModal);
        res.put("message", "Success");

        return new ResponseEntity<>(res, HttpStatus.OK);

    }

    public ResponseEntity<?> createDesignationList(Map<String,Object> param) {
        EmployeeDesignationModel employeeDesignationModel = new EmployeeDesignationModel();

        String designation = (String) param.get("designation");
        employeeDesignationModel.setDesignation(designation);
        employeeDesignationRepository.save(employeeDesignationModel);

        Map<String,Object> map1 = new HashMap<>();
        map1.put("success",true);
        map1.put("data",employeeDesignationModel);
        map1.put("message","success");


        return new ResponseEntity<>(map1, HttpStatus.OK);

    }

    public List<EmployeeDesignationModel> getAllDesignationList() {
        List<EmployeeDesignationModel> list1 = employeeDesignationRepository.findAll();
        return list1;
    }

    @Override
    public ResponseEntity<?> createDesignationfromDaoFinal(String designationname, Connection connection) {

        String desig_name = designationname;

        String sql = "insert into emp_designation(designation) values (?)";



        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,desig_name);

            int resultSet = preparedStatement.executeUpdate();

            if(resultSet == 0){
                throw new SQLException("No row affected");
            } else {
                return new ResponseEntity<>("Data save Successfully", HttpStatus.OK);
            }

        } catch (Exception e){
            throw new RuntimeException(e);
        }


    }


}
