package sk.posam.hoursalpha.api;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

public interface IHoursAlphaAPI {

    @PostMapping("/Auth/login")
    public Long login();
}
