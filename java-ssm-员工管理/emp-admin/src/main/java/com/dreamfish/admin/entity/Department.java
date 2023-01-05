package com.dreamfish.admin.entity;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.Pattern;

/**
 * @author: dreamfish
 * @Description: TODO
 * @date: Created in 2022/8/5-22:04
 * @version: v1.8
 **/
public class Department {
    private Integer deptId;
    @Pattern(regexp ="^[\\u4E00-\\u9FA5A-Za-z\\s]+[\\u4E00-\\u9FA5A-Za-z]+$",
    message = "请输入1~20位汉字或英文字母")
    @Length(min=1,max =20)
    private String deptName;
    private String location;
    @Pattern(regexp = "^((((19|20)\\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\\d|3[01]))|(((19|20)\\d{2})-(0?[469]|11)-(0?[1-9]|[12]\\d|30))|(((19|20)\\d{2})-0?2-(0?[1-9]|1\\d|2[0-8]))|((((19|20)([13579][26]|[2468][048]|0[48]))|(2000))-0?2-(0?[1-9]|[12]\\d)))$",
    message = "时间格式不正确")
    @Length(min =8 ,max=10,message ="时间格式不正确")
    private String establishTime;

    //员工人数
    private Integer count;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Department() {
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        //去除首尾空格
        deptName=deptName.trim();
        this.deptName = deptName;
    }

    public String getLocation() {

        return location;
    }

    public void setLocation(String location) {
        //去除首尾空格
        location=location.trim();
        this.location = location;
    }

    public String getEstablishTime() {
        return establishTime;
    }

    public void setEstablishTime(String establishTime) {
        this.establishTime = establishTime;
    }

    @Override
    public String toString() {
        return "Department{" +
                "deptId=" + deptId +
                ", deptName='" + deptName + '\'' +
                ", location='" + location + '\'' +
                ", establishTime=" + establishTime +
                '}';
    }
}
