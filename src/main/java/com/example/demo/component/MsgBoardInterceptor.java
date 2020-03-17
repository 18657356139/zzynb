package com.example.demo.component;

import com.example.demo.mode.User;
import com.example.demo.tool.CommonUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class MsgBoardInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception
    {
        User user = (User)request.getSession().getAttribute("user");
        if (user == null || CommonUtil.isStrEmpty(user.getUsername())){
            String requestURI = request.getRequestURI();
            request.getSession().setAttribute("toUrl", requestURI);
            response.sendRedirect("/pages/login.html");
            return false;
        }
        return true;
    }

}
