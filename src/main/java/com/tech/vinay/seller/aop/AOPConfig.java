package com.tech.vinay.seller.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AOPConfig {

//
//    // Executes before any method in UserController
//    @Before("execution(* com.tech.vinay.seller.controllers.UserController.*(..))")
//    public void beforeService() {
//        System.out.println("Before executing method in UserController1.");
//    }
//
//    // Executes after any method in UserController (success or failure)
//    @After("execution(* com.tech.vinay.seller.controllers.UserController.*(..))")
//    public void afterService() {
//        System.out.println("After executing method in UserController2.");
//    }
//
//    // Executes if the method returns successfully
//    @AfterReturning(pointcut = "execution(* com.tech.vinay.seller.controllers.UserController.*(..))", returning = "result")
//    public void afterReturningService(Object result) {
//        System.out.println("After successfully returning from method in UserController3.");
//        System.out.println("Method returned value is: " + result);
//    }
//
//    // Executes if the method throws an exception
//    @AfterThrowing(pointcut = "execution(* com.tech.vinay.seller.controllers.UserController.*(..))", throwing = "exception")
//    public void afterThrowingService(Exception exception) {
//        System.out.println("Exception thrown in method in UserController4: " + exception.getMessage());
//    }
//
//    // Wraps around the method execution (both before and after)
//    @Around("execution(* com.tech.vinay.seller.controllers.UserController.*(..))")
//    public Object aroundService(ProceedingJoinPoint joinPoint) throws Throwable {
//        System.out.println("Before executing method in UserController5.");
//
//        // Proceed with the method execution
//        Object result = null;
//        try {
//            result = joinPoint.proceed();
//            System.out.println("After successfully executing method in UserController6.");
//        } catch (Exception e) {
//            System.out.println("Exception caught in @Around advice: " + e.getMessage());
//            throw e;  // Rethrow the exception to allow normal error handling
//        }
//
//        // Return the original method's result
//        return result;
//    }

    @Pointcut("execution(* com.tech.vinay.seller.controllers.UserController.*(..))")
    public void logBeforeAndAfter(){
    }
    @Around("logBeforeAndAfter()")
    public Object Log(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        try{
        String methodName = String.valueOf(proceedingJoinPoint.getClass());

        System.out.println("method start..."+methodName);
        Object result=proceedingJoinPoint.proceed();
        System.out.println("Class Name..."+proceedingJoinPoint.getClass());
        System.out.println("data save successfully in DB");
        }
        catch(Exception e){
            throw new RuntimeException("Something went wrong");

        return result;
    }
}
