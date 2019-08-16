package com.oocl.jpa.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employee_record")
public class EmployeeRecord {

    @Id
    @GeneratedValue
    private int id;
    private String name;

    private String detail;

}
