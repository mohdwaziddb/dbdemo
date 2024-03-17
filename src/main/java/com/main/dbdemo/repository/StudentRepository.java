package com.main.dbdemo.repository;

import com.main.dbdemo.entities.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository <StudentEntity,Long> {

}
