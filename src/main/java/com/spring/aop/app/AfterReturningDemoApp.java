package com.spring.aop.app;

import com.spring.aop.config.DemoConfig;
import com.spring.aop.dao.AccountDAO;
import com.spring.aop.dto.Account;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class AfterReturningDemoApp {

    public static void main(String[] args) {

        // read spring config java class
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);

        // get the bean from spring container
        AccountDAO accountDAO = context.getBean("accountDAO", AccountDAO.class);

        // call method to find the accounts
        List<Account> accounts = accountDAO.findAccounts(false);

        // display the accounts
        System.out.println("accounts = " + accounts);

        // close the context
        context.close();

    }

}
