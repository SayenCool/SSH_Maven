package com.cskaoyan.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpSession;
import java.util.Map;

public class ValidationInterceptor extends MethodFilterInterceptor {

    private static Logger logger = LogManager.getLogger(ValidationInterceptor.class);

    @Override
    protected String doIntercept(ActionInvocation invocation) throws Exception {
        /*
            ERROR 1:java.lang.IllegalStateException: getAttribute: Session already invalidated

            异常原因：
            从Session中获取属性值的时候，Session已经无效。
            （1）Session timeout；
            （2）程序中调用了session.invalidate()方法。

            ERROR 2:Method public java.lang.String java.util.AbstractMap.toString() threw an exception when invoked on {}

            如果同时报了这两个错，很有可能就是jsp页面的<s:debug></s:debug>标签的问题
            展开debug可以看到错误。
            解决方案有2：
            1. 不用s:debug
            2. session.invalidate() -> session.removeAttribute()
        */

        ActionContext context = invocation.getInvocationContext();

        /*
        // 方式1：
        HttpSession session = ServletActionContext.getRequest().getSession(true);
        if (!session.isNew()) {
            Object employee = session.getAttribute("employee");
            if (null != employee) {
                logger.info("拦截器校验通过！employee=" + employee);
                return invocation.invoke();
            }
            logger.info("拦截器校验不通过！employee=" + employee);
        }*/

        // 方式2：
        Map<String, Object> session = context.getSession();
        if (null != session) {
            Object employee = session.get("employee");
            if (null != employee) {
                logger.info("拦截器校验通过！employee=" + employee);
                // 没有删除key-value这种？先置空
                context.put("loginRequired", "");
                return invocation.invoke();
            }
            logger.info("拦截器校验不通过！employee=" + employee);
        }

        context.put("loginRequired", "请您先登陆！");
        logger.info("session invalidation！");
        return "error";
    }
}
