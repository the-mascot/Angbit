package com.oracle.Angbit.service;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

public class LoginInterceptor implements HandlerInterceptor {

    public LoginInterceptor() {
    }

    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler,
                           ModelAndView modelAndView) throws IOException {
        System.out.println("postHandle called.");
    }

    public boolean preHandle(HttpServletRequest request,
                          HttpServletResponse response,
                          Object handler) throws IOException {
        //선수행
        System.out.println("preHandle Interceptor Called.");
        HandlerMethod method = (HandlerMethod)handler;
        Method methodObj = method.getMethod();
        System.out.println("Bean : "+method.getBean());
        System.out.println("Method : "+methodObj);
        String id = (String) request.getSession().getAttribute("sessionID");
        if (id != null) {
            System.out.println("ID is... -> "+id);
            return true;
        } else {
            System.out.println("ID is null. Please Login.");
            response.sendRedirect("gologin");
            return false;
        }


    }
}
