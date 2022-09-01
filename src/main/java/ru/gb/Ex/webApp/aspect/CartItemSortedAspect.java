package ru.gb.Ex.webApp.aspect;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import ru.gb.Ex.webApp.dto.CartItem;

import java.util.Comparator;
import java.util.List;

@Component
@Aspect
@Slf4j
@Order(0)
public class CartItemSortedAspect {

  /*
    @Before("ru.gb.Ex.webApp.aspect.MyPointcuts.sortCartItems()")
    public void sortCart(JoinPoint joinPoint){

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        log.info("Calling: " + signature.getMethod());
    }*/


    @AfterReturning(pointcut = "ru.gb.Ex.webApp.aspect.MyPointcuts.sortCartItems()",
    returning = "items")
    public void sortCartItems(List<CartItem> items) {


      items.sort(Comparator.comparing(CartItem::getTitle));

    }

}
