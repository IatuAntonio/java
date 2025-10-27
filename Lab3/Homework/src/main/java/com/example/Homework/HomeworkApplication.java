package com.example.Homework;

import com.example.Homework.model.Order;
import com.example.Homework.repository.CustomerRepository;
import com.example.Homework.service.OrderService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HomeworkApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomeworkApplication.class, args);
	}

    @Bean
    public CommandLineRunner run(ApplicationContext context) {
        return args -> {
            System.out.println("\n-----Starting Order Processing-----\n");

            OrderService orderService = context.getBean(OrderService.class);

            Order order1 = new Order(1L, 1L, 1200);
            Order order2 = new Order(2L, 2L, 450.0);
            Order order3 = new Order(3L, 3L, 700.0);

            orderService.processOrder(order1);
            orderService.processOrder(order2);
            orderService.processOrder(order3);

            System.out.println("\n-----Order Processing Completed-----\n");
        };
    }
}
