package com.zrrd.mybatis.pojo;

import java.io.Serializable;
import java.util.Objects;

public class Emp implements Serializable {

    private static final long serialVersionUID = 2126491995885818589L;

    private Integer id;
    private String name;
    private String job;
    private Double salary;

    public Emp() {
    }

    public Emp(Integer id, String name, String job, Double salary) {
        this.id = id;
        this.name = name;
        this.job = job;
        this.salary = salary;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Emp emp = (Emp) o;
        return Objects.equals(id, emp.id) && Objects.equals(name, emp.name) && Objects.equals(job, emp.job) && Objects.equals(salary, emp.salary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, job, salary);
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", job='" + job + '\'' +
                ", salary=" + salary +
                '}';
    }
}
