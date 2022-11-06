package com.nhnacademy.gw1.parking.repository;

import com.nhnacademy.gw1.parking.entity.User;

import java.util.HashMap;
import java.util.Map;

public class PaycoMember {
    public static Map<Integer, User> paycoMember = new HashMap<>();

    public void putMember(int userId, User user) {
        paycoMember.put(userId, user);
    }

    public User getMember(int userId) {
        return paycoMember.get(userId);
    }
}
