package com.main.dbdemo.services;

import com.main.dbdemo.custom_repo.SQLQueryImpl;
import com.main.dbdemo.database.MySqlDatabase;
import com.main.dbdemo.entities.EmployeeDesignationModel;
import com.main.dbdemo.entities.EmployeesModel;
import com.main.dbdemo.entities.StudentEntity;
import com.main.dbdemo.entities.StudentProfileEntity;
import com.main.dbdemo.repository.EmployeeDesignationRepository;
import com.main.dbdemo.repository.EmployeesRepository;
import com.main.dbdemo.repository.StudentProfileRepository;
import com.main.dbdemo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.util.*;

@Service
public class StudentServicesImpl implements StudentServices {

    @Autowired
    StudentProfileRepository studentProfileRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    EmployeeDesignationRepository employeeDesignationRepository;

    @Autowired
    EmployeesRepository employeesRepository;

    @Autowired
    SQLQueryImpl sqlQuery;

    static Connection connection = null;
    static {
        try {
        connection = MySqlDatabase.connect();
        System.out.println("connection established");
        } catch (Exception e){
            System.out.println(e);
        }
    }

    public  Map<String,Map<String,Object>> createStudent(Map<String,String> param) throws Exception {

        StudentEntity studentEntity = new StudentEntity();

        StudentProfileEntity studentProfileEntity = new StudentProfileEntity();
        String name = param.get("name");
        String fathername = param.get("fathername");
        String mothername = param.get("mothername");
        String classname = param.get("classname");
        String sectionname = param.get("sectionname");

        studentProfileEntity.setMothername(mothername);
        studentProfileEntity.setFathername(fathername);

        studentProfileRepository.save(studentProfileEntity);

        Long id1 = studentProfileRepository.findAllByOrderByIdDesc().get(0).getId();
//        Long id = studentProModel.getId();

        studentEntity.setStudentprofileid(id1);
        studentEntity.setName(name);
        studentEntity.setClassname(classname);
        studentEntity.setSectionname(sectionname);

        studentRepository.save(studentEntity);

        Map<String,Map<String,Object>> map = new LinkedHashMap<>();
        Map<String,Object> map1 = new LinkedHashMap<>();
        map1.put("name",name);
        map1.put("fathername",fathername);
        map1.put("mothername",mothername);
        map1.put("classname",classname);
        map1.put("sectionname",sectionname);

        map.put("data",map1);


        return map;
    }

    public  List<Map<Long,Object>> getDetails(Map<String,String> param) throws Exception {

        List<Map<Long,Object>> list1 = new ArrayList();
        Map<Object,Object> map1 = new HashMap<>();

        List<StudentProfileEntity> student_profile_all = studentProfileRepository.findAll();
        student_profile_all.forEach(obj->{
            Long id = obj.getId();
            map1.put(id,obj);
        });

        List<StudentEntity> student_all = studentRepository.findAll();
        Map<Long,Object> map2 = new LinkedHashMap<>();

        student_all.forEach(obj->{
            Long studentprofileid = obj.getStudentprofileid();
            StudentProfileEntity st_profile = (StudentProfileEntity) map1.get(studentprofileid);
            map2.put(studentprofileid,st_profile);
            //System.out.println(st_profile);
            //map1.put(studentprofileid,st_profile);
        });

        System.out.println(map2);
        list1.add(map2);
        
        
        Map<Long,Object> designation_map = new LinkedHashMap<>();
        List<EmployeeDesignationModel> designation_lists = employeeDesignationRepository.findAll();
        designation_lists.forEach(obj->{
            Long deignation_id = obj.getId();
            designation_map.put(deignation_id,obj);
        });

        Map<Long,Object> finalDesignationMap = new LinkedHashMap<>();
        //List<EmployeesModel> allByDesignation_id = new LinkedList<>();

        List<EmployeesModel> employees_list = employeesRepository.findAll();


        List<List<Map<String, Object>>> employeedesignationList_SQL = new ArrayList<>();

        HashSet<Long> set1 = new HashSet<>();
        for(EmployeesModel obj :employees_list){
            Long designationId = obj.getDesignationid();
            if(designationId!=null && designationId!=-1 && designationId!=0) {
                set1.add(designationId);
            }
        }
        String setString = "";
        for(Long value1 : set1){
            setString += value1+ " ,";
        }
//        char c = setString.charAt(setString.length() - 1);
//        String c1=Character.toString(c);
//        setString = setString.replace(c1,"");
        StringBuilder string = new StringBuilder(setString);
        string.setCharAt(setString.length()-1, ' ');
        setString=string.toString();


        for(EmployeesModel obj :employees_list){
            Long designationId = obj.getDesignationid();

            if(designationId!=null && designationId!=-1 && designationId!=0){
                Object designationModal = designation_map.get(designationId);
                designation_map.put(designationId,designationModal);

                //List<EmployeesModel> allByDesignation_id = employeesRepository.findAllByDesignationid(designationId);
                //finalDesignationMap.put(designationId,allByDesignation_id);

                //String setString =set1.toString();
                //String substring = setString.substring(1, 5);


                List<EmployeesModel> employeeByDesignationidQuery = employeesRepository.findEmployeeByDesignationid(designationId);
                finalDesignationMap.put(designationId,employeeByDesignationidQuery);



            }

            //finalDesignationMap.put(designationId,designationModal);
        }

        List<Map<String, Object>> employeebyDesignationID = sqlQuery.getEmployeebyDesignationID(setString, connection);
        employeedesignationList_SQL.add(employeebyDesignationID);

        System.out.println(finalDesignationMap);
        System.out.println(employeedesignationList_SQL);

        List<Map<Long,Object>> list2 = new ArrayList<>();
        list2.add(finalDesignationMap);


        return list2;
    }
}
