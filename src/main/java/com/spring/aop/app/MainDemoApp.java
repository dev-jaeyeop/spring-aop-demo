package com.spring.aop.app;

import com.spring.aop.config.DemoConfig;
import com.spring.aop.dao.AccountDAO;
import com.spring.aop.dao.MembershipDAO;
import com.spring.aop.dto.Account;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainDemoApp {

    public static void main(String[] args) {

        // read spring config java class
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);

        // get the bean from spring container
        AccountDAO accountDAO = context.getBean("accountDAO", AccountDAO.class);
        MembershipDAO membershipDAO = context.getBean("membershipDAO", MembershipDAO.class);

        // call the business method
        Account account = new Account();
        account.setName("accountName");
        account.setLevel("accountLevel");

        accountDAO.addAccount(account, true);
        accountDAO.doWork();
        accountDAO.setName("name");
        accountDAO.setServiceCode("service");

        String name = accountDAO.getName();
        String serviceCode = accountDAO.getServiceCode();

        membershipDAO.addSillyMember();
        membershipDAO.goToSleep();

        // close the context
        context.close();

    }

}
