package com.techgen.contoller;

import com.techgen.model.Employee;
import com.techgen.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("")
    public ResponseEntity<List<Employee>> getEmployees() {
        List<Employee> employees = employeeService.getEmployees();
        return ResponseEntity.ok().header("custom-header", "employee").body(employees);
    }

    @GetMapping("/{employee-id}")
    public ResponseEntity<Employee> getEmployeeId(@PathVariable("employee-id") int id) {
        Employee employee = employeeService.getEmployeeById(id);
        return ResponseEntity.ok().header("custom-header", "        return ResponseEntity.ok().header(\"custom-header\", \"student\").body(employee);\n").body(employee);
    }

    @GetMapping("/name")
    public ResponseEntity<List<Employee>> getEmployeeByName(@RequestParam("first-name") String firstName, @RequestParam("last-name") String lastName) {
        List<Employee> employees = employeeService.getEmployeeByName(firstName, lastName);
        return ResponseEntity.ok().header("custom-header", "employee").body(employees);
    }

    @PostMapping("")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employeeReq) {
        Employee employee = employeeService.createEmployee(employeeReq);
        return ResponseEntity.status(HttpStatus.CREATED).header("custom-header", "employee").body(employee);
    }

    @PutMapping("/{employee-id}")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employeeReq) {
        Employee employee = employeeService.updateEmployee(employeeReq);
        return ResponseEntity.ok().header("custom-header", "employee").body(employee);
    }

    @DeleteMapping("/{employee-id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("employee-id") int id) {
        String status = employeeService.deleteEmployee(id);
        return ResponseEntity.ok().header("custom-header", "employee").body(status);
    }
}
