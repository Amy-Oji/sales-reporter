package com.amyojiakor.Report.Application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
//@EnableKafka // this is required to enable Kafka listener annotations
@ComponentScan(basePackages = "com.amyojiakor.Report.Application")
public class ReportApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReportApplication.class, args);
	}

}
