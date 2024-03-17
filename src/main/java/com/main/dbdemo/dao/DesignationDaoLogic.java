package com.main.dbdemo.dao;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Service
public class DesignationDaoLogic implements DesignationDaoInterface {

    @Override
    public void createDesignationfromDao(EmployeeDesignationDao employeeDesignationDao, Connection conn) {

        String designation = employeeDesignationDao.getDesignation();

        String sql = "insert into emp_designation(designation) values (?)";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,designation);

            int resultSet = preparedStatement.executeUpdate();

            if(resultSet == 0){
                throw new SQLException("No row affected");
            }

        } catch (SQLException e){
            throw new RuntimeException(e);
        }

    }
}
