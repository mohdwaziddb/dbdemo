package com.main.dbdemo.custom_repo;

import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

@Service
public class SQLQueryImpl implements SQLQuery {

    public List<Map<String,Object>> getEmployeebyDesignationID(String designationid, Connection connection) {

        //String sql = "SELECT * FROM emp_userdetails WHERE emp_designationid="+designationid;
        String sql = "SELECT * FROM emp_userdetails WHERE emp_designationid in ("+designationid+")";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            List<Map<String,Object>> list = new LinkedList<>();


            while (rs.next()){
                Map<String,Object> map1 = new LinkedHashMap<>();
                String emp_name = rs.getString("emp_name");
                String emp_phoneno = rs.getString("emp_phoneno");
                String emp_email = rs.getString("emp_email");
                Long emp_designationid = rs.getLong("emp_designationid");

                map1.put("name",emp_name);
                map1.put("phoneno",emp_phoneno);
                map1.put("email",emp_email);
                map1.put("designationid",emp_designationid);

                list.add(map1);
            }
            return list;
        } catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    public Map<String,Object> getEmployeebyDesignationALl(Connection connection) {
        //String limit = "limit 11";
        String sql = "SELECT * FROM emp_designation ";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            List<Map<String,Object>> list = new LinkedList<>();


            while (rs.next()){
                Map<String,Object> map1 = new LinkedHashMap<>();
                String id = rs.getString("id");
                String designation = rs.getString("designation");

                map1.put("id",id);
                map1.put("designation",designation);

                list.add(map1);
            }

            Map<String,Object> map2 = new LinkedHashMap<>();
            map2.put("designation_array",list);


            return map2;
        } catch (Exception e){
            throw new RuntimeException(e);
        }

    }

}
