package com.dreamfish.admin.entity;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.Pattern;

/**
 * @author: dreamfish
 * @Description: TODO
 * @date: Created in 2022/8/5-22:00
 * @version: v1.8
 **/
public class Employee {
    private Integer empId;
    @Pattern(regexp ="^[\\u4E00-\\u9FA5A-Za-z\\s]+(·[\\u4E00-\\u9FA5A-Za-z]+)*$",
            message = "请输入汉字或英文字母(.)")
    @Length(min=1,max =20,message = "长度应为1~20")
    private String empName;
    private Integer gender;
   @Length(max=25,min=1,message = "长度为1~25")
   @Pattern(regexp = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+",message = "邮箱格式不正确")
    private String email;
    private Department department;

    private Integer deptId;

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    //springmvc转换日期
    @Pattern(regexp = "^((((19|20)\\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\\d|3[01]))|(((19|20)\\d{2})-(0?[469]|11)-(0?[1-9]|[12]\\d|30))|(((19|20)\\d{2})-0?2-(0?[1-9]|1\\d|2[0-8]))|((((19|20)([13579][26]|[2468][048]|0[48]))|(2000))-0?2-(0?[1-9]|[12]\\d)))$",
            message = "时间格式不正确")
    @Length(min = 8,max = 10,message = "时间格式不正确")
    private String hireDate;
    public Employee() {
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        //去除首尾空格
        empName=empName.trim();
        this.empName = empName;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getEmail() {
        //去除首尾空格
        return email;
    }

    public void setEmail(String email) {
        email=email.trim();
        this.email = email;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getHireDate() {
        return hireDate;
    }

    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empId=" + empId +
                ", empName='" + empName + '\'' +
                ", gender=" + gender +
                ", email='" + email + '\'' +
                ", department=" + department +
                ", hireDate=" + hireDate +
                '}';
    }
}
