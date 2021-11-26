package com.oracle.Angbit.service.myInfo;

import com.oracle.Angbit.dao.myInfo.myInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WidrawInterceptor implements HandlerInterceptor {

    @Autowired
    private myInfoDao midao;

    public WidrawInterceptor() {
    }

    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler,
                           ModelAndView modelAndView) throws IOException {

    }

    public boolean preHandle(HttpServletRequest request,
                          HttpServletResponse response,
                          Model model,
                          Object handler) throws IOException {
        //선수행
        System.out.println("Widrawal preHandle Interceptor Called.");
        String id = request.getParameter("id");
        System.out.println("로그인 요청 아이디 : "+id);
        if(midao.chkWidraw(id)==true){
            model.addAttribute("chk", "탈퇴한 아이디입니다.");
            response.sendRedirect("gologin");
            return false;
        } else {
            return true;
        }

    }
}
