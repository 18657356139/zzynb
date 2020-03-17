package com.example.demo.controller;

import com.example.demo.mode.Msg;
import com.example.demo.mode.ResponseData;
import com.example.demo.mode.User;
import com.example.demo.service.MsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class MsgController {

    @Autowired
    MsgService msgService;

    @RequestMapping("/addMsg")
    @ResponseBody
    public String addMsg(HttpSession session, Msg msg) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            msg.setUsername(user.getUsername());
            try {
                msgService.addMsg(msg);
            } catch (RuntimeException e) {
                e.printStackTrace();
                return "error";
            }
            return "success";
        }
        return "Error: No User in session.";
    }

    @RequestMapping("/getAllMsg")
    @ResponseBody
    public ResponseData getAllMsg(HttpSession session){
        ResponseData result = new ResponseData();
        User user = (User) session.getAttribute("user");
        result.setCurrentUser(user.getUsername());
        result.setData(msgService.selectMsgs());
        return result;
    }
}
