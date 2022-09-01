package ru.gb.Ex.webApp.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j

public class ExceptionAspect {

    @AfterThrowing(pointcut = "ru.gb.Ex.webApp.aspect.MyPointcuts.allControllersMethods()",
    throwing = "exception")
    public void exceptionLogging(Throwable exception){

        log.info("Исключение выброшено: " + exception.toString());

    }

}
