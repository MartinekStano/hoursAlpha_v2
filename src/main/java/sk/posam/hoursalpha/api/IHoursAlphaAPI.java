package sk.posam.hoursalpha.api;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

public interface IHoursAlphaAPI {
    @GetMapping("noAuth/test")
    String noAuthTest();

    @GetMapping("Auth/test")
    String withAuthTest();
}
