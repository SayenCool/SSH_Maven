<%--
  Created by IntelliJ IDEA.
  User: Sayen Cool
  Date: 2018/7/2
  Time: 23:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>增加用户</title>
    <style type="text/css">
        a {
            color: blue;
        }
    </style>
    <script type="text/javascript" src="../../js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../../js/jquery.min.js"></script>
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
                        if (data == "1") {    //表示可用
                            $this.after("<font color='green'>该姓名可以使用！</font>");
                        } else if (data == "0") {    //表示不可用
                            $this.after("<font color='red'>该姓名已存在！</font>");
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
<s:if test="uuid != null">
    <h1>修改用户</h1>
</s:if>
<s:else>
    <h1>添加用户</h1>
</s:else>
<hr>
<s:form action="emp-save" method="post">
    <s:if test="uuid != null">
        <s:textfield name="name" label="姓名" disabled="true"/>
        <s:hidden name="uuid"/>
    </s:if>
    <s:else>
        <s:textfield name="name" label="姓名"/>
    </s:else>
    <s:textfield name="pwd" label="密码"/>
    <s:textfield name="email" label="邮箱"/>

    <s:textfield name="birthday" label="出生日期">
        <s:param name="value"><s:date name="birthday" format="yyyy-MM-dd"/></s:param>
    </s:textfield>

    <s:select list="#request.departments" listKey="uuid" listValue="name" name="department.uuid" label="部门"/>
    <s:if test="uuid != null">
        <s:submit value="修改"/>
    </s:if>
    <s:else>
        <s:submit value="添加"/>
    </s:else>

</s:form>

<%-- 如果href="index.jsp" 由于在WEB-INF目录下，会被隐藏，所以无法访问 --%>
<a href="emp-list">回到首页</a>

<s:debug></s:debug>
</body>
</html>
