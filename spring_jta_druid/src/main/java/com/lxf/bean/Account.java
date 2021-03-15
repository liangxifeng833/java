package com.lxf.bean;

public class Account {
    private int userId;
    private double money;
    public Account(){}

    Account(int userId, double money){
        this.userId = userId;
        this.money = money;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String toString() {
        return "Account{" +
                "money='" + money + '\'' +
                ", userId=" + userId +
                '}';
    }
}
