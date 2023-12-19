package com.ticketbookingsys.metro.aspect;

import com.ticketbookingsys.metro.entity.STATION_TYPE;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Slf4j
@Aspect
@Component
public class StationValidationAspect {

    @Before(value = "@annotation(com.ticketbookingsys.metro.annotations.StationValidation)")
    public void beforeAdvice(JoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        for (Object object : args) {
            if (object != null) {
                STATION_TYPE stationType = null;
                Long price = null;
                for (Field field : object.getClass().getDeclaredFields()) {
                    field.setAccessible(true);
                    String fieldName = field.getName();
                    Object fieldValue = field.get(object);
                    switch (fieldName) {
                        case "price" -> price = (Long) fieldValue;
                        case "stationType" -> stationType = (STATION_TYPE) fieldValue;
                        default -> { }
                    }
                }
                if(stationType.equals(STATION_TYPE.LUX) || stationType.equals(STATION_TYPE.SEMI_LUX)) {
                    if(price < 20) throw new Error("For " + stationType + " Station, the price should be at least 20.");
                }
                if(stationType.equals(STATION_TYPE.NON_LUX)) {
                    if(price < 50) throw new Error("For " + stationType + " Station, the price should be at least 50.");
                }
            }
        }
    }

}
