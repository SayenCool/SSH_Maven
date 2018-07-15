package com.cskaoyan.service.impl;

import com.cskaoyan.dao.DepartmentDao;
import com.cskaoyan.entity.Department;
import com.cskaoyan.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("departmentService")
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentDao departmentDao;

    /**
     * 根据 UUID 查询该部门信息
     */
    @Override
    public Department getDepartment(String uuid) {
        return departmentDao.findOne(uuid);
    }

    /**
     * 根据部门名称查询该部门信息
     */
    @Override
    public Department getDepartmentByName(String name) {
        return departmentDao.findDepartmentByName(name);
    }

    @Override
    public void deleteDepartment(Department department) {
        departmentDao.delete(department);
    }

    @Override
    public void deleteDepartment(List<Department> departments) {
        departmentDao.delete(departments);
    }

    /**
     * 获得全部部门信息
     */
    @Override
    public List<Department> getAllDepartment() {
        return (List<Department>) departmentDao.findAll();
    }

    @Override
    public boolean isExistByName(String d_name) {
        return departmentDao.findDepartmentByName(d_name) != null;
    }

    /**
     * 保存或更新部门
     */
    @Override
    public void saveOrUpdateDepartment(Department department) {
        departmentDao.save(department);
    }

    /**
     * 批量保存或更新部门
     */
    @Override
    public void saveOrUpdateDepartment(List<Department> departmentList) {
        departmentDao.save(departmentList);
    }

}
