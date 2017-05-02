package com.whc.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by whc on 2017/3/29.
 */
@Entity
@Table(name = "sbt_student")
public class Student implements Serializable {

    private static final long serialVersionUID = -2013495207407539906L;

    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "AGE")
    private Integer age;

    /** 0,1 */
    @Column(name = "GENDER", nullable = false)
    private Integer gender;

    @Column(name = "CREATE_TIME")
    private Date createTime;

    @Column(name = "UPDATE_TIME")
    private Date updateTime;

    public Student() {
    }

    public Student(Integer id, String name, Integer age, Integer gender, Date createTime, Date updateTime) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Student(String name, Integer age, Integer gender, Date createTime, Date updateTime) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Student(String name, Integer age, Integer gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
