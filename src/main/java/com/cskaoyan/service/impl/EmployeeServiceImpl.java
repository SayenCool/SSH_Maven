package com.cskaoyan.service.impl;

import com.cskaoyan.dao.EmployeeDao;
import com.cskaoyan.entity.Employee;
import com.cskaoyan.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("employeeService")
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    /**
     * 验证用户是否存在
     */
    @Override
    public Employee isExist(String name, String pwd) {
        return employeeDao.findEmployeeByNameAndPwd(name, pwd);
    }

    /**
     * 更新或者保存用户
     */
    @Override
    @Transactional
    public void saveOrUpdateEmployee(Employee employee) {
        employeeDao.save(employee);
    }

    /**
     * 批量更新或者保存用户
     */
    @Override
    public void saveOrUpdateEmployee(List<Employee> employeeList) {
        employeeDao.save(employeeList);
    }

    /**
     * 获得所有的用户信息
     */
    @Override
    public List<Employee> getAllEmployee() {
        return (List<Employee>) employeeDao.findAll();
    }

    /**
     * 根据员工姓名查询该员工信息
     */
    @Override
    public List<Employee> getEmployeeByName(String name) {
        return employeeDao.findEmployeeByName(name);
    }

    /**
     * 删除员工信息
     */
    @Override
    public void deleteEmployee(Employee employee) {
        employeeDao.delete(employee);
    }

    /**
     * 批量删除员工信息
     */
    @Override
    public void deleteEmployee(List<Employee> employees) {
        employeeDao.delete(employees);
    }

    /**
     * 根据 UUID 获得该员工的信息
     */
    @Override
    public Employee getEmployee(String uuid) {
        return employeeDao.findOne(uuid);
    }
}
