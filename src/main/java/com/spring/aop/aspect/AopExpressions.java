package com.spring.aop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AopExpressions {

    @Pointcut("execution(* com.spring.aop.dao.*.*(..))")
    public void forDaoPackage() {
    }

    @Pointcut("execution(* com.spring.aop.*.*.get*(..))")
    public void getter() {
    }

    @Pointcut("execution(* com.spring.aop.*.*.set*(..))")
    public void setter() {
    }

    @Pointcut("forDaoPackage() && !(getter() || setter())")
    public void forDaoPackageNoGetterSetter() {
    }

}
