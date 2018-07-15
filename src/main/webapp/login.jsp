<%--
  Created by IntelliJ IDEA.
  User: Sayen Cool
  Date: 2018/7/2
  Time: 23:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>用户登录</title>
    <script type="text/javascript" src="js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript">
        $(function () {
            //检验用户名是否存在
            $(":input[name=name]").change(function () {
                var val = $(this).val();
                val = $.trim(val);
                var $this = $(this);

                if (val != "") {
                    $this.nextAll("font").remove();

                    var url = "emp-validateName";
                    var args = {"name": val};
                    $.post(url, args, function (data) {
                        if (data == "1") {    //表示不存在
                            $this.after("<font color='red'>不存在！</font>");
                        } else if (data == "0") {    //表示存在
                            $this.after("<font color='green'>姓名通过校验！</font>");
                        } else {    //表示服务器错误
                            alert("服务器错误！");
                        }
                    });
                } else {
                    alert("姓名不能为空！");
                    this.focus();
                }
            });
        });
    </script>
</head>
<body>
<s:if test="#session.employee != null">
    <h2>Welcome，<span style="color: orange"><s:property value="#session.employee.name"/></span></h2>
    <h3>您已登录，<span style="color: orange">3秒</span>后将跳转到您的主页！</h3>
    <h3>如果您想换个账号登录，请在您的主页注销</h3>
    <%
        response.setHeader("refresh", "3;url=emp-list");
    %>
</s:if>
<s:else>
    <h1>用户登录</h1>
    <hr>
    <span style="color: red"><s:property value="loginRequired"></s:property></span>
    <br>
    <s:form action="login" method="post">
        <s:textfield name="name" label="姓名"/>
        <s:password name="pwd" label="密码"/>
        <s:submit value="登录"/>
        <s:reset value="重置"/>
        <%--<input type="submit" value="登录"/>--%>
        <%--<input type="reset" value="重置"/>--%>
    </s:form>
</s:else>
<s:debug></s:debug>
</body>
</html>