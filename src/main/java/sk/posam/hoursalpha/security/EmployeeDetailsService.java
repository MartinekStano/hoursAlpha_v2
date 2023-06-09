package sk.posam.hoursalpha.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sk.posam.hoursalpha.controller.exception.NotActivatedAccountViaEmailException;
import sk.posam.hoursalpha.domain.Employee;
import sk.posam.hoursalpha.domain.repository.IEmployeeRepository;

import java.util.Collections;
import java.util.Optional;

@Service
public class EmployeeDetailsService implements UserDetailsService {

    @Autowired
    private IEmployeeRepository repository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Employee> employee = repository.findEmployeeByEmail(email);



        if(employee.isPresent()){

            if(!employee.get().isStatusOfProfile()) //TO DO
                throw new NotActivatedAccountViaEmailException();

            return new org.springframework.security.core.userdetails.User(
                    employee.get().getEmail(),
                    employee.get().getPassword(),
                    employee.get().isStatusOfProfile(),
                    true,
                    true,
                    true,
                    Collections.singleton(new SimpleGrantedAuthority("USER"))
            );
        }else {
            throw new UsernameNotFoundException("User was not found with username: " + email);
        }
    }
}
