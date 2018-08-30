package com.tzc.springboot.controller;

import com.tzc.springboot.entity.Department;
import com.tzc.springboot.response.Response;
import com.tzc.springboot.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: lujinqi
 * @Date: 2018/7/24 15:57
 */
@RestController
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;


    /**
     * 查询所有部门及其下属员工
     * @return
     */
    @GetMapping("/dept")
    public Response<List<Department>> selectAll(){
        List<Department> list = departmentService.selectAllWithUser();
        return Response.ok(list);
    }
}
