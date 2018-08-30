package com.tzc.springboot.service;

import com.tzc.springboot.entity.Department;

import java.util.List;

/**
 * @Author: lujinqi
 * @Date: 2018/7/24 15:52
 */
public interface DepartmentService {
    /**
     * 查询部门的用户
     * @return
     */
    List<Department> selectAllWithUser();
}
