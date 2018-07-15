package com.cskaoyan.service;

import com.cskaoyan.entity.Employee;

import java.util.List;

public interface EmployeeService {

    Employee isExist(String name, String pwd);    //验证用户是否存在

    void saveOrUpdateEmployee(Employee employee);    //更新或者保存用户

    void saveOrUpdateEmployee(List<Employee> employeeList); //批量更新或者保存用户

    List<Employee> getAllEmployee();    //获得所有的用户信息

    List<Employee> getEmployeeByName(String name);    //根据员工姓名查询该员工信息

    void deleteEmployee(Employee employee);    //删除员工信息

    void deleteEmployee(List<Employee> employees);    //批量删除员工信息

    Employee getEmployee(String uuid);    //根据 UUID 获得该员工的信息

}
