package ru.gb.Ex.webApp.aspect;

import org.aspectj.lang.annotation.Pointcut;

public class MyPointcuts {

    @Pointcut("execution(* ru.gb.Ex.webApp.controllers.*.*(..))")
    public void allControllersMethods(){}


    /* Поинткат для проверки очередности Advices для одного бизнес метода !
    @Pointcut("execution(* ru.gb.Ex.webApp.controllers.*.delete*(..))")
    public void aopTest(){}
    */

    @Pointcut("execution(* ru.gb.Ex.webApp.controllers.CartController.getAll())")
    public void sortCartItems(){}


}
