package ru.gb.Ex.webApp.services;


import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Getter
@Setter
public class PerformanceService {

    private Map<String, Long> performanceStats = new HashMap<>();


    public Map<String, Long> showStats(){
        return performanceStats;
    }



}
