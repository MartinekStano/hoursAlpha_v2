package sk.posam.hoursalpha.domain.repository;

import sk.posam.hoursalpha.domain.Employee;

import java.util.Optional;

public interface IEmployeeRepository {
    Optional<Employee> findEmployeeByEmail(String email);
    void saveNewEmployee(Employee employee);
}
