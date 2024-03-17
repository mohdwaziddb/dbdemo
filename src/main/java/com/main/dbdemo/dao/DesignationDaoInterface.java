package com.main.dbdemo.dao;

import java.sql.Connection;

public interface DesignationDaoInterface {

    public void createDesignationfromDao(EmployeeDesignationDao employeeDesignationDao, Connection conn);
}
