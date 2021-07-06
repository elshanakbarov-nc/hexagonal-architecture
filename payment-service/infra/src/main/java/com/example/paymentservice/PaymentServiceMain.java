package com.example.paymentservice;

import com.example.commons.DomainComponent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@ComponentScan(
        includeFilters = {
                @ComponentScan.Filter(type = FilterType.ANNOTATION, value = {DomainComponent.class})
        }
)
public class PaymentServiceMain {
    public static void main(String[] args) {
        SpringApplication.run(PaymentServiceMain.class, args);
    }
}
