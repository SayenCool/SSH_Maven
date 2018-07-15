package com.cskaoyan.action;

import com.cskaoyan.entity.Department;
import com.cskaoyan.entity.Employee;
import com.cskaoyan.service.DepartmentService;
import com.cskaoyan.service.EmployeeService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller("employeeAction")
@Scope("prototype")
public class EmployeeAction extends ActionSupport implements ModelDriven<Employee>, RequestAware, Preparable {

    private static final long serialVersionUID = 1L;

    private Map<String, Object> request;
    private Employee emp = new Employee();

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;
    private InputStream inputStream;

    public Map<String, Object> getRequest() {
        return request;
    }

    @Override
    public void setRequest(Map<String, Object> request) {
        this.request = request;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    @Override
    public Employee getModel() {
        return emp;
    }

    /**
     * 修改用户时获得该 UUID
     */
    ActionContext context = ActionContext.getContext();
    HttpServletRequest req = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
    String uuid = req.getParameter("uuid");

    /**
     * 获得所有员工的信息
     *
     * @return
     */
    public String list() {
        List<Employee> employees = employeeService.getAllEmployee();
        request.put("employees", employees);
        return "list";
    }

    /**
     * 跳转到修改、增加界面
     */
    @Override
    public String input() {
        List<Department> departments = departmentService.getAllDepartment();
        request.put("departments", departments);
        return "input";
    }

    /**
     * 修改界面的显示当前信息
     */
    public void prepareInput() {
        System.err.println("*******" + uuid + "********");
        if (uuid != null) {
            emp = employeeService.getEmployee(uuid);
        }
    }

    /**
     * 更新或者保存信息
     *
     * @return
     */
    public String save() {
        if (uuid == null) {
            emp.setCreateDate(new Date());
        }
        employeeService.saveOrUpdateEmployee(emp);
        return "success";
    }

    public void prepareSave() {
        System.out.println("****************save****************");
        if (uuid == null) {
            emp = new Employee();
        } else {
            emp = employeeService.getEmployee(uuid);
        }
    }

    /**
     * 员工的删除
     * @return
     */
    /*public String delete() {
        employeeService.deleteEmployee(emp);
        return "delete";
    }*/

    /**
     * 员工的 Ajax 删除
     *
     * @return
     */
    public String delete() {
        try {
            employeeService.deleteEmployee(emp);
            inputStream = new ByteArrayInputStream("1".getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            try {
                inputStream = new ByteArrayInputStream("0".getBytes("UTF-8"));
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }
        }
        return "ajax-success";
    }

    /**
     * 用户的修改
     *
     * @return
     */
    public String update() {
        List<Department> departments = departmentService.getAllDepartment();
        request.put("departments", departments);
        return "update";
    }

    /**
     * 检验用户名是否存在
     *
     * @return
     * @throws UnsupportedEncodingException：抛出的话，前端可以显示服务器错误
     */
    public String validateName() throws UnsupportedEncodingException {
        System.err.println(employeeService.getEmployeeByName(emp.getName()));
        if (employeeService.getEmployeeByName(emp.getName()).size() > 0) {
            inputStream = new ByteArrayInputStream("0".getBytes("UTF-8"));
        } else {
            inputStream = new ByteArrayInputStream("1".getBytes("UTF-8"));
        }
        return "ajax-success";
    }

    @Override
    public void prepare() throws Exception {
    }
}
