package com.example.demo.controller;

import com.example.demo.mode.User;
import com.example.demo.service.UserService;
import com.example.demo.tool.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;

@Controller()
public class Login {
    @Autowired
    UserService userService;
//    @Autowired
//    HttpServletResponse response;

    @RequestMapping("/")
    @ResponseBody
    public String index(){
        return "redirect:/pages/index.html";
    }

    @RequestMapping("/login")
    @ResponseBody
    public String login(HttpSession session, User user) throws IOException {
        if (user == null || CommonUtil.isStrEmpty(user.getUsername(), user.getPswd())) {
            return "Username or Password cannot be null.";
        }
        if(!userService.checkUserFromDb(user)){
            return "Username and Password are NOT matching.";
        }
        session.setAttribute("user", user);
//        String toUrl = (String) session.getAttribute("toUrl");
//        if (!CommonUtil.isStrEmpty(toUrl) && !toUrl.endsWith("login.html")) {
//            session.setAttribute("toUrl", null);
//            return "redirect:" + toUrl;
//        }
        return "redirect:/pages/index.html";
    }

    @RequestMapping("/register")
    @ResponseBody
    public String register(User user) {
        if (user == null || CommonUtil.isStrEmpty(user.getUsername(), user.getPswd())) {
            return "Username or Password cannot be null";
        }
        int effectRow = 0;
        String result;
        try {
            effectRow = userService.register(user);
        } catch (RuntimeException e) {
            return "Username already exists.";
        } catch (Exception e){
            return "Internal Error occurred";
        }
        if (effectRow > 0) {
            result = "success";
        } else {
            result = "error";
        }
        return result;
    }
}
