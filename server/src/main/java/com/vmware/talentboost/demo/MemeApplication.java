package com.vmware.talentboost.demo;


import com.vmware.talentboost.demo.service.DomainService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;



@SpringBootApplication
public class MemeApplication {

    public static void main(String[] args) throws Exception {
        ApplicationContext context = SpringApplication.run(MemeApplication.class, args);
        DomainService domainController = context.getBean(DomainService.class);
        context.getAutowireCapableBeanFactory();
    }
}

