package com.cskaoyan.dao;

import com.cskaoyan.entity.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//@NoRepositoryBean
//继承Repository等价于注解@RepositoryDefinition(domainClass = Employee.class, idClass = String.class)
public interface EmployeeDao extends CrudRepository<Employee, String> {

    Employee findEmployeeByNameAndPwd(String name, String pwd);

    List<Employee> findEmployeeByName(String name);
}
