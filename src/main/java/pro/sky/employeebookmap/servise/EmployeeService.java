package pro.sky.employeebookmap.servise;

import pro.sky.employeebookmap.exception.EmployeeAlreadyAddedException;
import pro.sky.employeebookmap.exception.EmployeeNotFoundException;
import pro.sky.employeebookmap.exception.EmployeeStorageIsFullException;
import pro.sky.employeebookmap.model.Employee;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class EmployeeService {
    private static final int EMPLOYEE_COUNT = 10;
    private final Map<String, Employee> employees = new HashMap<>();

    public Employee createEmployee(String firstName, String lastName) {
        if (employees.size() >= EMPLOYEE_COUNT) {
            throw new EmployeeStorageIsFullException();
        }
        Employee employee = new Employee(firstName, lastName);
        String key = employee.getFirstName() + " " + employee.getLastName();

        if (employees.containsKey(key)) {
            throw new EmployeeAlreadyAddedException();
        }
        employees.put(key, employee);
        return employee;
    }

    public Employee removeEmployee(String firstName, String lastName) {
        String key = firstName + " " + lastName;
        Employee target = new Employee(firstName, lastName);
        if (!employees.containsKey(key)) {
            throw new EmployeeNotFoundException();
        }
        employees.remove(key);
        return target;
    }

    public Employee findEmployee(String firstName, String lastName) {
        String key = firstName + " " + lastName;
        if (!employees.containsKey(key)) {
            throw new EmployeeNotFoundException();
        }
        return employees.get(key);
    }

    public Collection<Employee> getEmployees() {
        return employees.values();
    }
}