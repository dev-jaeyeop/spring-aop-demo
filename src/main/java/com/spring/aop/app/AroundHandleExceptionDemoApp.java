package com.spring.aop.app;

import com.spring.aop.config.DemoConfig;
import com.spring.aop.service.TrafficFortuneService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.logging.Logger;

public class AroundHandleExceptionDemoApp {

    private static final Logger logger = Logger.getLogger(AroundHandleExceptionDemoApp.class.getName());

    public static void main(String[] args) {

        // read spring config java class
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);

        // get the bean from spring container
        TrafficFortuneService trafficFortuneService = context.getBean("trafficFortuneService", TrafficFortuneService.class);

        logger.info("trafficFortuneService.getFortune()...");
        String data = trafficFortuneService.getFortune(true);
        logger.info("My fortune is: " + data);
        logger.info("Finished");

        // close the context
        context.close();

    }

}
