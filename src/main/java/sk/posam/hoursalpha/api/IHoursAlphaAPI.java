package sk.posam.hoursalpha.api;


import org.springframework.web.bind.annotation.*;
import sk.posam.hoursalpha.api.dto.EmployeeDto;

public interface IHoursAlphaAPI {

    @PostMapping("/Auth/login")
    Long login();

    @PostMapping("/noAuth/register")
    void register(@RequestBody EmployeeDto dto);

    @PostMapping("/noAuth/verify/{token}")
    void activationEmailAddress(@PathVariable String token);
}
