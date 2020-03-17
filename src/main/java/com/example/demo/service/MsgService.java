package com.example.demo.service;

import com.example.demo.dao.MsgDao;
import com.example.demo.mode.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MsgService {
    @Autowired
    MsgDao msgDao;

    @Transactional
    public void addMsg(Msg msg) throws RuntimeException{
        int effectRow = msgDao.addMsg(msg);
        if (effectRow == 0){
            throw new RuntimeException();
        }
    }

    public List<Msg> selectMsgs(){
        return msgDao.selectMsgs(100);
    }
}
