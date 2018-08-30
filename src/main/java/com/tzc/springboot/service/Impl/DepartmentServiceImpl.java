package com.tzc.springboot.service.Impl;

import com.tzc.springboot.entity.Department;
import com.tzc.springboot.mapper.DepartmentMapper;
import com.tzc.springboot.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: lujinqi
 * @Date: 2018/7/24 15:58
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public List<Department> selectAllWithUser() {
        return departmentMapper.selectWithUser();
    }
}
