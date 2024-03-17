package com.main.dbdemo.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity

@Getter
@Setter
@Table(name = "studentprofiles")
public class StudentProfileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fathername;

    private String mothername;

}
