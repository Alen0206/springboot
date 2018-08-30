package com.tzc.springboot.entity;

import javax.persistence.*;
import java.io.Serializable;

public class User implements Serializable {


    private static final long serialVersionUID = -1060339629881388111L;
    /**
     * 用户Id
     */
    @Id
    private Integer id;

    private String username;

    private String password;

    private Integer age;

    private String email;

    @Column(name = "dept_id")
    private Integer deptId;

    /**
     * 获取用户Id
     *
     * @return id - 用户Id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置用户Id
     *
     * @param id 用户Id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return age
     */
    public Integer getAge() {
        return age;
    }

    /**
     * @param age
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return dept_id
     */
    public Integer getDeptId() {
        return deptId;
    }

    /**
     * @param deptId
     */
    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }
}