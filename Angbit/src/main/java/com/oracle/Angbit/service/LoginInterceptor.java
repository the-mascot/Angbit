package com.oracle.Angbit.service;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginInterceptor implements HandlerInterceptor {

    public LoginInterceptor() {
    }

    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler,
                           ModelAndView modelAndView) throws IOException {

    }

    public boolean preHandle(HttpServletRequest request,
                          HttpServletResponse response,
                          Object handler) throws IOException {
        //선수행
        System.out.println("Login preHandle Interceptor Called.");
//        HandlerMethod method = (HandlerMethod)handler;
//        Method methodObj = method.getMethod();
//        System.out.println("Bean : "+method.getBean());
//        System.out.println("Method : "+methodObj);
        String id = (String) request.getSession().getAttribute("sessionID");
        if (id != null) {
            return true;
        } else {
            response.sendRedirect("loginForm");
            return false;
        }

    }
}
