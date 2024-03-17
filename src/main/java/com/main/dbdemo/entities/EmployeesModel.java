package com.main.dbdemo.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "emp_userdetails")
public class EmployeesModel {

    @Column(name = "emp_name")
    private String empname;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long empid;

    @Column(name = "emp_email")
    private String empemail;

    @Column(name = "emp_phoneno")
    private String empphoneno;

    @Column(name = "password")
    private String password;

    @Column(name = "emp_designationid")
    private Long designationid;
}
