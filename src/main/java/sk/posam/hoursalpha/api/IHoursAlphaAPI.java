package sk.posam.hoursalpha.api;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import sk.posam.hoursalpha.api.dto.EmployeeDto;

public interface IHoursAlphaAPI {

    @PostMapping("/Auth/login")
    public Long login();

    @PostMapping("/noAuth/register")
    public void register(@RequestBody EmployeeDto dto);
}
