package com.main.dbdemo.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "students")
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long studentprofileid;

    @Column
    private String name;

    @Column
    private String classname;

    @Column
    private String sectionname;

}
