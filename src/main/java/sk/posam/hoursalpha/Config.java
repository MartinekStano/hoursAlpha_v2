package sk.posam.hoursalpha;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import sk.posam.hoursalpha.application.repositoryCrud.IEmployeeJpaRepository;
import sk.posam.hoursalpha.domain.Employee;
import sk.posam.hoursalpha.security.PasswordEncoderProvider;

@Component
public class Config {

    @Bean
    CommandLineRunner commandLineRunner (IEmployeeJpaRepository repo, PasswordEncoderProvider passwordEncoderProvider) {
        return  args -> {
            String password = passwordEncoderProvider.getEncoder().encode("1234");
            Employee employee = new Employee("Martin",
                    "Stano",
                    "mato@gmail.com",
                    password,
                    "0911455600",
                    true);

            repo.save(employee);

        };
    }
}
