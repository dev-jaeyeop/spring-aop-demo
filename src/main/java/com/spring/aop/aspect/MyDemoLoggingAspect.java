package com.spring.aop.aspect;

import com.spring.aop.dto.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Logger;

@Order(1)
@Component
@Aspect
public class MyDemoLoggingAspect {

    private final Logger logger = Logger.getLogger(getClass().getName());

    @Around("execution(* com.spring.aop.service.*.getFortune(..))")
    public Object aroundGetFortune(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        // print out method we are advising on
        String method = proceedingJoinPoint.getSignature().toShortString();
        logger.info("@Around method = " + method);

        // get begin timeStamp
        Long begin = System.currentTimeMillis();

        // now, let's execute the method
        Object proceed;

        /**
         * custom Exception message
         */

/*
        try {
            proceed = proceedingJoinPoint.proceed();
        } catch (Exception e) {
            // log the exception
            logger.warning(e.getMessage());

            // give user a custom message
            proceed = "custom message";
        }
*/

        try {
            proceed = proceedingJoinPoint.proceed();
        } catch (Exception e) {
            // log the exception
            logger.warning(e.getMessage());

            // rethrow exception
            throw e;
        }

        // get and timeStamp
        Long end = System.currentTimeMillis();

        // compute duration and display it
        Long duration = end - begin;
        logger.info("duration = " + duration / 1000.0 + " seconds");

        return proceed;
    }

    @After("execution(* com.spring.aop.dao.AccountDAO.findAccounts(..))")
    public void afterFinallyFindAccountsAdvice(JoinPoint joinPoint) {

        // print out which method we are advising on
        String method = joinPoint.getSignature().toShortString();
        logger.info("@After method = " + method);
    }

    @AfterThrowing(pointcut = "execution(* com.spring.aop.dao.AccountDAO.findAccounts(..))", throwing = "exception")
    public void afterThrowingFindAccountsAdvice(JoinPoint joinPoint, Throwable exception) {

        // print out which method we are advising on
        String method = joinPoint.getSignature().toShortString();
        logger.info("@AfterThrowing method = " + method);

        // log the exception
        logger.info("exception = " + exception);
    }

    @AfterReturning(pointcut = "execution(* com.spring.aop.dao.AccountDAO.findAccounts(..))", returning = "result")
    public void afterReturningFindAccountsAdvice(JoinPoint joinPoint, List<Account> result) {

        // print out which method we are advising on
        String method = joinPoint.getSignature().toShortString();
        logger.info("@AfterReturning method = " + method);

        // print out the results of the method call
        logger.info("result = " + result);

        // let's post-process the data ... let's modify it

        // convert the account names to uppercase
        convertAccountNamesToUpperCase(result);

        logger.info("result = " + result);
    }

    @Before("com.spring.aop.aspect.AopExpressions.forDaoPackageNoGetterSetter()")
    public void beforeAddAccountAdvice(JoinPoint joinPoint) {
        logger.info("==== beforeAddAccountAdvice ====");
        // display the method signature
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        logger.info("@Before method: " + methodSignature);

        // display method arguments

        // get args
        Object[] args = joinPoint.getArgs();

        // loop thur args
        for (Object tempArg : args) {
            logger.info(String.valueOf(tempArg));

            if (tempArg instanceof Account) {

                // downcast and print Account specific stuff
                Account account = (Account) tempArg;
                logger.info("account name: " + account.getName());
                logger.info("account level: " + account.getLevel());
            }
        }

    }

    private void convertAccountNamesToUpperCase(List<Account> result) {

        // loop through accounts
        for (Account tempAccount : result) {

            // get upperCase version of name
            String upperName = tempAccount.getName().toUpperCase();

            // update the name on the account
            tempAccount.setName(upperName);
        }

    }

}
