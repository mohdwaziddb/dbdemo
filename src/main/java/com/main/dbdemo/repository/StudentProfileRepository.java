package com.main.dbdemo.repository;

import com.main.dbdemo.entities.StudentProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentProfileRepository extends JpaRepository <StudentProfileEntity,Long> {

    List<StudentProfileEntity> findAllByOrderByIdDesc();

}
