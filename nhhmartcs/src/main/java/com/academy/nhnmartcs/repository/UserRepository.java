package com.academy.nhnmartcs.repository;

import com.academy.nhnmartcs.domain.User;

import java.util.Map;

public interface UserRepository {

    public User getUser(String id);

    public boolean exist(String id);

    public Map<String, User> getUserMap();



}
