package com.main.dbdemo.services;

import com.main.dbdemo.custom_repo.EmployeesData;
import com.main.dbdemo.dao.DesignationDaoLogic;
import com.main.dbdemo.dao.EmployeeDesignationDao;
import com.main.dbdemo.entities.EmployeeDesignationModel;
import com.main.dbdemo.entities.EmployeesModel;
import com.main.dbdemo.repository.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class EmployeesServiceImpl implements EmployeesServices {

    @Autowired
    private EmployeesData employeesData;

    @Autowired
    EmployeesRepository employeesRepository;

    @Autowired
    DesignationDaoLogic designationDaoLogic;


    public ResponseEntity<?>  checkLogin(Map<String,Object> param) throws Exception {
        return employeesData.checkLoginDetails(param);
    }


    public Map<String, Object> getEmployeeData(Map<String,Object> param) throws Exception {
        Map<String,Object> map1 = new HashMap<>();
        List<EmployeesModel> list = new ArrayList<>();
        list = employeesData.getAllEmployeesList();
        map1.put("employee_array", list);
        return map1;
    }


    public ResponseEntity<?> createEmployee(Map<String,Object> param) throws Exception {
        return employeesData.createEmployeesList(param);
    }


    public Map<String,Object> employeeListbyname(Map<String,Object> param) throws Exception {
        String name = (String) param.get("name");
        List <EmployeesModel> list =  employeesRepository.findbyname(name);
        Map<String,Object> map1 = new HashMap<>();
        map1.put("data",list);
        return  map1;
    }

    public Map<String, Object> deleteEmployeebyID(Map<String,Object> param) throws Exception {
        String idinString = (String) param.get("id");
        Long id = Long.parseLong(idinString);

        List<EmployeesModel> delete_id_data = employeesRepository.findbyid(id);
        employeesRepository.deletebyid(id);
        Map<String, Object> map1 = new HashMap<>();
        map1.put("deleted_data",delete_id_data);
        map1.put("message","Success");
        map1.put("success",true);

        return map1;
    }

    public ResponseEntity<?> createDesignation(Map<String,Object> param) throws Exception {
        return employeesData.createDesignationList(param);
    }

    public Map<String, Object> getDesignationData(Map<String,Object> param) throws Exception {
        Map<String,Object> map1 = new HashMap<>();
        List<EmployeeDesignationModel> list = new ArrayList<>();
        list = employeesData.getAllDesignationList();
        map1.put("designation_array", list);
        return map1;
    }


    public List<EmployeesModel> emplist1() throws Exception {

        List<EmployeesModel> allEmployeesList = employeesData.getAllEmployeesList();
        Map<Long,String> map1 = new LinkedHashMap<>();
        for(EmployeesModel emp : allEmployeesList){
            Long empid = emp.getEmpid();
            String empname = emp.getEmpname();
            map1.put(empid,empname);
        }

        List list1 = new LinkedList();
        //list1.add(map1);

        Map<Long, String> collect = allEmployeesList.stream().collect(Collectors.toMap(EmployeesModel::getEmpid, EmployeesModel::getEmpname));
        List<EmployeesModel> collect1 = allEmployeesList.stream().filter(e -> (e.getEmpid() != 1l)).collect(Collectors.toList());
        //Map<Long, String> collect = allEmployeesList.stream().collect(Collectors.toMap(EmployeesModel::getEmpid, EmployeesModel::getEmpname));

        list1.add(collect);
        return collect1;
    }

    @Override
    public ResponseEntity<?> createDesignationfromDao(String designationname, Connection conn) {

        EmployeeDesignationDao employeeDesignationDao = new EmployeeDesignationDao();
        employeeDesignationDao.setDesignation(designationname);
        designationDaoLogic.createDesignationfromDao(employeeDesignationDao,conn);

        return new ResponseEntity<>("Data Save", HttpStatus.OK);

        //String desgination_name = designationname;
        //return employeesData.createDesignationfromDaoFinal(desgination_name, conn);
    }


}
