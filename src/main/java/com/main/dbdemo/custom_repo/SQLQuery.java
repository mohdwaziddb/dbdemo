package com.main.dbdemo.custom_repo;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public interface SQLQuery {

    List<Map<String,Object>> getEmployeebyDesignationID(String designationid, Connection connection);

    Map<String,Object> getEmployeebyDesignationALl(Connection connection);

}
