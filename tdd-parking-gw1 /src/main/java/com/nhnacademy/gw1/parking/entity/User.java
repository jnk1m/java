package com.nhnacademy.gw1.parking.entity;

import com.nhnacademy.gw1.parking.exception.NotEnoughMoneyException;

public class User {
    int userId;
    Wallet wallet;

    public User(int userId, int balance) {
        this.userId = userId;
        this.wallet = new Wallet(userId, balance);
    }

    public int getUserId() {
        return userId;
    }

    public int getMoneyFromWallet() {
        return wallet.getBalance();
    }

    public boolean payFee(int fee) {
        int walletBalance = getMoneyFromWallet();

        if (walletBalance < fee) { //지갑 잔액이 계산할 요금보다 적다면 NotEnoughMoneyException 에러 발생
            throw new NotEnoughMoneyException(fee);
        }
        wallet.setBalance(walletBalance - fee);
        return true;
    }


    private class Wallet {
        private final int userId;
        private int balance;

        public Wallet(int userId, int balance) {
            this.userId = userId;
            this.balance = balance;
        }

        public int getBalance() {
            return balance;
        }

        public void setBalance(int balance) {
            this.balance = balance;
        }
    }
}
