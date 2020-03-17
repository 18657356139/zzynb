package com.example.demo.controller;

import com.example.demo.mode.InitUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class TestApi {
    private static Map<Long, InitUser> usermap = new HashMap<>();

    @RequestMapping("/test")
    @ResponseBody
    public String test(){
        return "Hello world!";
    }

    @RequestMapping("/inituser")
    @ResponseBody
    public InitUser inituser(@RequestParam(name = "userid", required = false) Long id){
        InitUser user = usermap.get(id);
        if (user == null){
            user = new InitUser();
            usermap.put(user.getId(), user);
        }
        return user;
    }

    @RequestMapping("/excu")
    @ResponseBody
    public void excu(@RequestParam(name = "userid") Long id){
        InitUser user = usermap.get(id);
        if (user==null){
            return;
        }
        user.setMoney(user.getMoney()-10);
    }

    @RequestMapping("/lobster")
    public String lobster(){
        return "lobster";
    }
}
