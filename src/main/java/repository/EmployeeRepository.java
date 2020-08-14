package repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import model.Employee;
@Repository
public interface EmployeeRepository extends MongoRepository<Employee,String>{
public Employee findByFirstname(String first_name);
}
