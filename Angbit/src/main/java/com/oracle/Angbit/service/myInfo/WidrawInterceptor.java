package com.oracle.Angbit.service.myInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WidrawInterceptor implements HandlerInterceptor {

    @Autowired
    private MyInfoService mis;

    public WidrawInterceptor() {
    }

    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler,
                           ModelAndView modelAndView) throws IOException {
        System.out.println("WidrawInterceptor Posthandle End.");

    }

    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws IOException {
        //선수행
        System.out.println("Widrawal preHandle Interceptor Called.");
        String id = request.getParameter("id");
        System.out.println("로그인 요청 아이디 : " + id);
        boolean chk = mis.chkWidraw(id);
        System.out.println("chk?"+chk);
        if (id == "대기") {
//            request.setAttribute("chk", "탈퇴한 아이디입니다.");
            response.sendRedirect("/lg/loginWidraw");
            return false;
        } else {
            return true;
        }
    }




}
