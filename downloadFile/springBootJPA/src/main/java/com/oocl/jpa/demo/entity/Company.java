package com.tw.apistackbase.entity;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="company")
public class Company {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String address;
    @OneToMany
    @JoinColumn(name="company_id")
    private List<Employee> employees;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public List<Employee> getEmployees() {
        return employees;
    }
    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}