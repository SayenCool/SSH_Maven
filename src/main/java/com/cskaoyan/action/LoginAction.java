package com.cskaoyan.action;

import com.cskaoyan.entity.Employee;
import com.cskaoyan.service.EmployeeService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;

@Controller("loginAction")
@Scope("prototype")
public class LoginAction extends ActionSupport implements ModelDriven<Employee> {

    private Employee employee = new Employee();

    @Autowired
    EmployeeService employeeService;

    @Override
    public Employee getModel() {
        return employee;
    }

    public String login() {
        Employee employee = employeeService.isExist(this.employee.getName(), this.employee.getPwd());
        if (null != employee) {
            HttpSession session = ServletActionContext.getRequest().getSession(true);
            session.setAttribute("employee", employee);
            return SUCCESS;
        } else {
            return ERROR;
        }
    }

    public String logout() {
        HttpSession session = ServletActionContext.getRequest().getSession(false);
        if (null != session) {
            session.removeAttribute("employee");
//            session.invalidate();
            // s:debug 会报错
        }
        return SUCCESS;
    }

}
