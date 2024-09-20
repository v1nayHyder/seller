package com.tech.vinay.seller.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class DataSourceTracker {

    @Around("target(javax.sql.DataSource")
    public Object logDatasourceConnectionInfo(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String  name= String.valueOf(proceedingJoinPoint.getSignature());
        System.out.println(name+" Datasource connection started");
        Object result=proceedingJoinPoint.proceed();
        System.out.println("datasource ended "+result);
        return name;

    }
}
