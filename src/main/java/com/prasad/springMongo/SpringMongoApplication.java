package com.prasad.springMongo;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

//import model.Employee;
import repository.EmployeeRepository;

@SpringBootApplication
@ComponentScan("controller")
@EnableMongoRepositories(basePackageClasses = EmployeeRepository.class)
public class SpringMongoApplication {

	public static void main(String[] args)  {
		SpringApplication.run(SpringMongoApplication.class, args);
	}
	
	

}
