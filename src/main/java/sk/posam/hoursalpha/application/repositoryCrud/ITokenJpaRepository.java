package sk.posam.hoursalpha.application.repositoryCrud;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.posam.hoursalpha.domain.Employee;
import sk.posam.hoursalpha.domain.VerificationToken;

import java.util.Optional;

public interface ITokenJpaRepository extends JpaRepository<VerificationToken, Long> {
    Optional<VerificationToken> findByToken(String token);
    Optional<VerificationToken> findByEmployee(Employee employee);
}
