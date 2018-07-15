<%@ page isELIgnored="false" language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>首页</title>
    <style type="text/css">
        a {
            color: blue;
        }
    </style>
    <script type="text/javascript" src="../../js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../../js/jquery.min.js"></script>
    <script type="text/javascript">
        $(function () {
            //提示框
            $(".delete").click(function () {
                var name = $(this).next(":input").val();
                var flag = confirm("确定要删除  " + name + " 的信息吗？");
                if (flag) {
                    var $tr = $(this).parent().parent();
                    var url = this.href;// "emp-delete";
                    var args = {};
                    $.post(url, args, function (data) {
                        if (data == "1") {
                            alert("删除成功！");
                            $tr.remove();
                        } else {
                            alert("删除失败！");
                        }
                    });
                }
                //取消删除
                return false;
            });
        });
    </script>
</head>
<body>
<h1>Welcome，<span style="color: orange"><s:property value="#session.employee.name"/></span></h1>
<hr>
<a href="emp-input">增加用户</a> &nbsp;&nbsp;&nbsp;&nbsp;<a href="logout">注销</a>
<br><br>
<s:if test="#request.employees == null || #request.employees.size() == 0">没有任何员工的信息！</s:if>
<s:else>
    <table border="1" cellpadding="5" cellspacing="0">
        <tr>
            <td>UUID</td>
            <td>姓名</td>
            <td>密码</td>
            <td>出生日期</td>
            <td>邮箱</td>
            <td>部门名称</td>
            <td>创建时间</td>
            <td>操作</td>
        </tr>
        <s:iterator value="#request.employees" var="e">
            <tr>
                <td>${e.uuid}</td>
                <td>${e.name}</td>
                <td>${e.pwd}</td>
                <td>${e.birthday}</td>
                <td>${e.email}</td>
                <td>${e.department.name}</td>
                <td>${e.createDate}</td>
                <td>
                    <a href="emp-input?uuid=${e.uuid }">修改</a> &nbsp;&nbsp;&nbsp;
                    <a href="emp-delete?uuid=${e.uuid }" class="delete">删除</a>
                    <input type="hidden" value="${e.name}"/>
                </td>
            </tr>
        </s:iterator>
    </table>
</s:else>

<s:debug></s:debug>
</body>
</html>
