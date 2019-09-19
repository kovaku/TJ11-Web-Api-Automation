package com.github.domain;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetEmployeeResponse {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("employee_name")
    private String employeeName;
    @JsonProperty("employee_salary")
    private Long employeeSalary;
    @JsonProperty("employee_age")
    private Integer age;
    @JsonProperty("profile_image")
    private String profileImage;

    public GetEmployeeResponse() {}

    public GetEmployeeResponse(Integer id, String employeeName, Long employeeSalary, Integer age, String profileImage) {
        this.id = id;
        this.employeeName = employeeName;
        this.employeeSalary = employeeSalary;
        this.age = age;
        this.profileImage = profileImage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Long getEmployeeSalary() {
        return employeeSalary;
    }

    public void setEmployeeSalary(Long employeeSalary) {
        this.employeeSalary = employeeSalary;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        GetEmployeeResponse that = (GetEmployeeResponse) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(employeeName, that.employeeName) &&
            Objects.equals(employeeSalary, that.employeeSalary) &&
            Objects.equals(age, that.age) &&
            Objects.equals(profileImage, that.profileImage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, employeeName, employeeSalary, age, profileImage);
    }

    @Override
    public String toString() {
        return "CreateEmployeeResponse{" +
            "id=" + id +
            ", employeeName='" + employeeName + '\'' +
            ", employeeSalary=" + employeeSalary +
            ", age=" + age +
            ", profileImage='" + profileImage + '\'' +
            '}';
    }
}
