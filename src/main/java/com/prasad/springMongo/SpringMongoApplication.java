package com.prasad.springMongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import model.Employee;
import repository.EmployeeRepository;

@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = EmployeeRepository.class)
public class SpringMongoApplication implements CommandLineRunner {

	public static void main(String[] args)  {
		SpringApplication.run(SpringMongoApplication.class, args);
	}
	@Autowired
	private EmployeeRepository repo;

	@Override
	public void run(String... args) throws Exception {
	Employee emp = repo.save(new Employee("rahul","sharma",25000.00));
	Employee emp2 = repo.save(new Employee("rajesh","shinde",45000.00));
	System.out.println("1st object:"+emp.toString());
	System.out.println("1st object:"+emp2.toString());
	
	
	System.out.println("Employee found with findAll():");
    System.out.println("-------------------------------");
    for (Employee employee : repo.findAll()) {
      System.out.println(employee.toString());
    }
    System.out.println();
		
	}

}
