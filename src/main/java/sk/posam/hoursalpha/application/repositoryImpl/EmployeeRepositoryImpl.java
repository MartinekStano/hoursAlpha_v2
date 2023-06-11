package sk.posam.hoursalpha.application.repositoryImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import sk.posam.hoursalpha.application.repositoryCrud.IEmployeeJpaRepository;
import sk.posam.hoursalpha.domain.Employee;
import sk.posam.hoursalpha.domain.repository.IEmployeeRepository;

import java.util.Optional;

@Repository
public class EmployeeRepositoryImpl implements IEmployeeRepository {

    @Autowired
    private IEmployeeJpaRepository repository;
    @Override
    public Optional<Employee> findEmployeeByEmail(String email) {
        return repository.findEmployeeByEmail(email);
    }

    @Override
    public Optional<Employee> saveNewEmployee(Employee employee) {
        return Optional.of(repository.save(employee));
    }
}
