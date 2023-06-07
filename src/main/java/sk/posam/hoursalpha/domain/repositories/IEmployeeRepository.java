package sk.posam.hoursalpha.domain.repositories;

import sk.posam.hoursalpha.domain.Employee;

import java.util.Optional;

public interface IEmployeeRepository {
    Optional<Employee> findEmployeeByEmail(String email);
}
