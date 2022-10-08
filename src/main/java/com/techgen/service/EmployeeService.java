package com.techgen.service;

import com.techgen.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EmployeeService {

    private static final Map<Integer, Employee> employeeMap = new HashMap<>();

    static {
        employeeMap.put(1, new Employee(1, "Taher", "Ali"));
        employeeMap.put(2, new Employee(2, "Amit", "Sharma"));
    }

    public List<Employee> getEmployees() {
        return employeeMap.keySet().stream().map(employeeMap::get).collect(Collectors.toList());
    }

    public Employee getEmployeeById(int id) {
        return employeeMap.get(id);
    }

    public Employee createEmployee(Employee employee) {
        List<Integer> employeeIds = employeeMap.keySet().stream().toList();
        Optional<Integer> employeeIdOptional = employeeIds.stream().max(Integer::compareTo);
        int employeeId = 1;
        if (employeeIdOptional.isPresent()) {
            employeeId = employeeIdOptional.get();
            employee.setId(++employeeId);
            employeeMap.put(employee.getId(), employee);
        } else {
            employee.setId(employeeId);
            employeeMap.put(employeeId, employee);
        }
        return employeeMap.get(employee.getId());
    }

    public Employee updateEmployee(Employee employee) {
        List<Integer> employeeIds = employeeMap.keySet().stream().toList();
        boolean isEmployeeIdExist = employeeIds.stream().anyMatch(employeeId -> employeeId.equals(employee.getId()));
        if (isEmployeeIdExist) {
            Employee updatedEmployee = new Employee();
            updatedEmployee.setId(employee.getId());
            updatedEmployee.setFirstName(employee.getFirstName());
            updatedEmployee.setLastName(employee.getLastName());
            employeeMap.put(employee.getId(), updatedEmployee);
            return updatedEmployee;
        }
        return null;
    }

    public String deleteEmployee(int id) {
        List<Integer> employeeIds = employeeMap.keySet().stream().toList();
        boolean isEmployeeIdExist = employeeIds.stream().anyMatch(employeeId -> employeeId.equals(id));
        if (isEmployeeIdExist) {
            employeeMap.remove(id);
            return "removed employee id " + id;
        }
        return "employee id " + id + " not exist";
    }

    public List<Employee> getEmployeeByName(String firstName, String lastName) {
        return employeeMap.values().stream().filter(employee -> employee.getFirstName().equals(firstName) && employee.getLastName().equals(lastName)).collect(Collectors.toList());
    }
}
