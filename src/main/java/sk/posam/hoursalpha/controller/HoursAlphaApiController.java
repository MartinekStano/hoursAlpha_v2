package sk.posam.hoursalpha.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import sk.posam.hoursalpha.api.IHoursAlphaAPI;
import sk.posam.hoursalpha.api.dto.EmployeeDto;
import sk.posam.hoursalpha.application.IHoursAlphaApiService;

@RestController
public class HoursAlphaApiController implements IHoursAlphaAPI {

    @Autowired
    IHoursAlphaApiService iHoursAlphaApiService;

    @Override
    public Long login() {
        return 1L;
    }

    @Override
    public void register(EmployeeDto dto) {
        iHoursAlphaApiService.register(dto);
    }
}
