package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import repository.EmployeeRepository;
import model.Employee;


@RestController
@RequestMapping("/api")
public class EmployeeController {
@Autowired
private EmployeeRepository repo;

@GetMapping("get/{id}")
public ResponseEntity<Employee> getRecordById(@PathVariable String id){
	 Optional<Employee> employeeData = repo.findById(id);

	  if (employeeData.isPresent()) {
	    return new ResponseEntity<>(employeeData.get(), HttpStatus.OK);
	  } else {
	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	  }	
}

@GetMapping("/getAll")
public ResponseEntity<List<Employee>> getAllRecord(@RequestParam(required=false) String id){
	 try {
		    List<Employee> employee = new ArrayList<Employee>();

		    if (id == null)
		      repo.findAll().forEach(employee::add);
		    else
		      repo.findById(id);

		    if (employee.isEmpty()) {
		      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		    }

		    return new ResponseEntity<>(employee, HttpStatus.OK);
		  } catch (Exception e) {
		    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		  }
}

@PostMapping("/post")
public ResponseEntity<Employee> create(@RequestBody Employee employee) {
	try {
	    Employee obj = repo.save(new Employee(employee.getFirstname(),employee.getLastname(),employee.getSalary()));
	    return new ResponseEntity<>(obj,HttpStatus.CREATED);
	  } catch (Exception e) {
	    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	  }
}

@PutMapping("/put/{id}")
public ResponseEntity<Employee> update(@PathVariable String id,@RequestBody Employee employee){
	Optional<Employee> employeeData = repo.findById(id);

	  if (employeeData.isPresent()) {
	    Employee object = employeeData.get();
	    object.setFirstname(employee.getFirstname());
	    object.setLastname(employee.getLastname());
	    object.setSalary(employee.getSalary());
	    return new ResponseEntity<>(repo.save(object), HttpStatus.OK);
	  } else {
	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	  }
}

@DeleteMapping("/delete/{id}")
public ResponseEntity<HttpStatus> deleteById(@PathVariable String id){
	try {
	    repo.deleteById(id);
	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	  } catch (Exception e) {
	    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	  }
}

@DeleteMapping("/deleteAll")
public ResponseEntity<HttpStatus> deleteAll(){
	try {
	    repo.deleteAll();
	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	  } catch (Exception e) {
	    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	  }
}

}
