package com.example.demo.mode;

import java.util.Date;

public class InitUser {
    private long id;
    private int money;

    public InitUser(){
        long time = new Date().getTime();
        setId(time);
        setMoney(200);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
