package com.cskaoyan.service;

import com.cskaoyan.entity.Department;

import java.util.List;

public interface DepartmentService {

    boolean isExistByName(String name);    //验证部门是否存在

    void saveOrUpdateDepartment(Department department);    //更新或者保存部门

    void saveOrUpdateDepartment(List<Department> departmentList);   //批量更新或者保存部门

    List<Department> getAllDepartment();    //获得所有的部门信息

    Department getDepartmentByName(String name);    //根据部门名称查询该部门信息

    void deleteDepartment(Department department);    //删除部门信息

    void deleteDepartment(List<Department> departments);    //批量删除部门信息

    Department getDepartment(String uuid);    //根据 UUID 获得该部门的信息

}
