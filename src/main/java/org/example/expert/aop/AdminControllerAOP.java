package org.example.expert.aop;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class AdminControllerAOP {

    @Pointcut("execution(* org.example.expert.domain.comment.controller.CommentAdminController.*(..))")
    public void commentAdminControllerMethods() {
    }
    @Pointcut("execution(* org.example.expert.domain.user.controller.UserAdminController.*(..))")
    public void userAdminControllerMethods() {
    }

    @Around("commentAdminControllerMethods() || userAdminControllerMethods()")
    public Object commentLogging(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            Object result = joinPoint.proceed();
            return result;
        } finally {
            long start = System.currentTimeMillis();
            log.info("log = {}" , joinPoint.getSignature());
            log.info("start = {}", start);
        }
    }
//    @Before("commentAdminControllerMethods() || userAdminControllerMethods()")

}
