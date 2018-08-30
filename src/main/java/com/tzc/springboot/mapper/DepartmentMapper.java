package com.tzc.springboot.mapper;

import com.tzc.springboot.entity.Department;
import com.tzc.springboot.util.MyMapper;

import java.util.List;

public interface DepartmentMapper extends MyMapper<Department> {

    List<Department> selectWithUser();
}