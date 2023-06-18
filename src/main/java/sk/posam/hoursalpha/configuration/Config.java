package sk.posam.hoursalpha.configuration;

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
                    "martinmatostano@gmail.com",
                    password,
                    "0911455600",
                    true,
                    "Konhora",
                    "01303",
                    2.5);

            repo.save(employee);

        };
    }
}
