package sk.posam.hoursalpha.controller;

import org.springframework.web.bind.annotation.RestController;
import sk.posam.hoursalpha.api.IHoursAlphaAPI;

@RestController
public class HoursAlphaApiController implements IHoursAlphaAPI {

    @Override
    public Long login() {
        return 1L;
    }
}
