package com.example.demo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import com.example.demo.exception.handler.TodoItemException;

@Aspect
@Configuration
public class AspectConfiguration {

     private Logger log = LoggerFactory.getLogger(AspectConfiguration.class);

     @Before(value="execution(* com.example.demo.controllers.*.*(..)))")
     public void LogBefore(JoinPoint joinPoint){
        log.info("Executing {}", joinPoint);
     }

     @AfterThrowing(value="execution(* com.example.demo.controllers.*.*(..))", throwing="ex")
     public void todoItemExceptionHandler(TodoItemException ex)  throws Throwable{
        log.info("=======Exception Caught======");
        log.info(ex.toString());

     }

     @AfterReturning(value="execution(* com.example.demo.controllers.*.*(..))")
     public void LogAfterReturning(JoinPoint joinPoint){
        log.info("Execution completed successfully - {}", joinPoint);
     }
}
