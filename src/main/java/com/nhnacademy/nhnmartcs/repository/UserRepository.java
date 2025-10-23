package com.nhnacademy.nhnmartcs.repository;

import com.nhnacademy.nhnmartcs.domain.User;

public interface UserRepository {

    boolean exists(String id);

    User getUser(String id);

    boolean matches(String id, String pwd);

    public void save(User user);


}
