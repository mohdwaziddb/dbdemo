package com.main.dbdemo.repository;

import com.main.dbdemo.entities.EmployeeDesignationModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeDesignationRepository extends JpaRepository<EmployeeDesignationModel,Long>
{

}
