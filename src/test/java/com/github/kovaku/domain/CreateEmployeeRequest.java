package com.github.kovaku.domain;

public class CreateEmployeeRequest {

    private String name;
    private Long salary;
    private Integer age;

    public CreateEmployeeRequest(String name, Long salary, Integer age) {
        this.name = name;
        this.salary = salary;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
