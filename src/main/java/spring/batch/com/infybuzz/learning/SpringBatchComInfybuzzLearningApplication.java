package spring.batch.com.infybuzz.learning;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableBatchProcessing
@ComponentScan({"spring.batch.com.infybuzz.config","spring.batch.com.infybuzz.service"})
public class SpringBatchComInfybuzzLearningApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBatchComInfybuzzLearningApplication.class, args);
	}

}
