package ru.gb.Ex.webApp.aspect;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import ru.gb.Ex.webApp.services.PerformanceService;

@Component
@Aspect
@Slf4j
@RequiredArgsConstructor
public class PerformanceAspect {

    private final PerformanceService performanceService;


    @Around("ru.gb.Ex.webApp.aspect.MyPointcuts.allControllersMethods()")
    public Object performanceTest(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        long startTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();

        long endTime = System.currentTimeMillis() - startTime;

       performanceService.getPerformanceStats().put(proceedingJoinPoint.getTarget().getClass().getSimpleName(), performanceService.getPerformanceStats().getOrDefault(proceedingJoinPoint.getTarget().getClass().getSimpleName(),0L) + endTime);
       

        log.info("Время работы метода: " + proceedingJoinPoint.getSignature() + " "  + endTime + " мс");
        return result;


    }



}
