import com.cskaoyan.entity.Department;
import com.cskaoyan.service.DepartmentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
//@Transactional
public class MyTest {

    @Autowired
    DepartmentService departmentService;

    @Test
//    @Rollback(false)
    public void initDepartmentTable() {
        /*ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        DepartmentService departmentService = ctx.getBean("departmentService", DepartmentService.class);*/

        // 使用session可以提交，是因为配置文件中事务的类写错
        // 应该使用jpa相关的事务类，而不是spring
        /*SessionFactory sessionFactory = (SessionFactory) ctx.getBean("sessionFactory");
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();*/

        List<Department> departments = new ArrayList<>();
        Department department = new Department();
        department.setName("生产部");
        departments.add(department);
//        session.save(department);
        department = new Department();
        department.setName("销售部");
        departments.add(department);
//        session.save(department);
        department = new Department();
        department.setName("研发部");
        departments.add(department);
//        session.save(department);
        department = new Department();
        department.setName("售后部");
        departments.add(department);
//        session.save(department);
        department = new Department();
        department.setName("总经办");
        departments.add(department);
//        session.save(department);

        departmentService.saveOrUpdateDepartment(departments);

//        session.save(department);
//        transaction.commit();
    }
}
