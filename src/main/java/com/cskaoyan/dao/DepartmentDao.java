package com.cskaoyan.dao;

import com.cskaoyan.entity.Department;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

// @NoRepositoryBean
// 一般用作父类的repository,有这个注解,spring不会去实例化该repository。
// 指明当前这个接口不是我们领域类的接口，该接口被JpaRepository继承
public interface DepartmentDao extends CrudRepository<Department, String> {

    Department findDepartmentByName(String name);
}
