package com.baizhi.aspect;

import com.baizhi.annotation.AddLog;
import com.baizhi.dao.LogMapper;
import com.baizhi.entity.Admin;
import com.baizhi.entity.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.UUID;

@Aspect    //该类是一个切面类
@Configuration      //将该类交给spring工厂管理
public class LogAspect {
        @Resource
        HttpSession session;
        @Resource
        LogMapper logMapper;


    @Around("@annotation(com.baizhi.annotation.AddLog)")
    public Object addLog(ProceedingJoinPoint proceedingJoinPoint){
        System.out.println("进入环绕通知");
        //谁在什么时间做了什么操作  是否成功
         //获取操作人名字
        Admin admin = (Admin) session.getAttribute("admin");
        String username = admin.getUsername();
        //获取操作时间
        Date date = new Date();
        //获取方法名
        String methodName = proceedingJoinPoint.getSignature().getName();
        //获取方法的签名
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        //获取方法
        Method method = signature.getMethod();
        //获取目标方法的注解
        AddLog addLog = method.getAnnotation(AddLog.class);
        //获取方法的值
        String value = addLog.value();
        //放行目标方法
        String message = null;
        Object proceed =null;
        try {
            proceed = proceedingJoinPoint.proceed();
            message = "执行成功";
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            message = "执行失败";
        }

        Log log = new Log(UUID.randomUUID().toString(), username, date, methodName + "( " + value + " )", message);

            logMapper.insert(log);
        return proceed;
    }
}
