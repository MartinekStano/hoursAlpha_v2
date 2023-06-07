package sk.posam.hoursalpha.controller;

import org.springframework.web.bind.annotation.RestController;
import sk.posam.hoursalpha.api.HoursAlphaAPI;

@RestController
public class HoursAlphaApiController implements HoursAlphaAPI {

    @Override
    public String noAuthTest() {
        return "It's work with no auth!";
    }

    @Override
    public String withAuthTest() {
        return "It's work with auth!";
    }
}
