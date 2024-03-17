package com.main.dbdemo.repository;

import com.main.dbdemo.entities.EmployeesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import javax.transaction.Transactional;
import java.util.List;

public interface EmployeesRepository extends JpaRepository <EmployeesModel,Long>
{

    @Query("select e from EmployeesModel e where e.empname = :name ")
    List<EmployeesModel> findbyname(@Param("name") String name);

    @Query("select e from EmployeesModel e where e.empid = :id ")
    List<EmployeesModel> findbyid(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query("delete from EmployeesModel e where e.empid = :id ")
    void deletebyid(@Param("id") Long id);

    @Query("select e from EmployeesModel e where e.empname = :username and e.password = :password ")
    EmployeesModel checkLoginCredential(@Param("username") String username, @Param("password") String password);


    List<EmployeesModel> findAllByDesignationid(Long id);



    @Query("select e from EmployeesModel e where e.designationid = :designationid ")
    List<EmployeesModel> findEmployeeByDesignationid(@Param("designationid") Long designationid);


}
