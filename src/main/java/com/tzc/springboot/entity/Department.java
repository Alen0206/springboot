package com.tzc.springboot.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
public class Department {
    @Id
    private Integer id;

    private String name;

    private List<User> userList;
}