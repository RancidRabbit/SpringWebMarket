package ru.gb.Ex.webApp.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.Ex.webApp.services.PerformanceService;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class PerformanceController {


    private final PerformanceService performanceService;


    @GetMapping("/stats")
    public Map<String, Long> showStats(){
        return performanceService.getPerformanceStats();
    }

}
