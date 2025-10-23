package com.nhnacademy.nhnmartcs.repository;

import com.nhnacademy.nhnmartcs.domain.Cs;
import com.nhnacademy.nhnmartcs.domain.Customer;
import com.nhnacademy.nhnmartcs.domain.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final Map<String, User> userMap = new HashMap<>();

    // 샘플 데이터 삽입
    public UserRepositoryImpl() {
        Customer user1 = new Customer("eugene", "qwer", "Eugene", new ArrayList<>());
        Customer user2 = new Customer("suin", "qwer", "Suin", new ArrayList<>());
        Cs cs1 = new Cs("illoong", "qwer", "Illoong");
        Cs cs2 = new Cs("gyujin", "qwer", "gyujin");

        userMap.put(user1.getId(), user1);
        userMap.put(user2.getId(), user2);
        userMap.put(cs1.getId(), cs1);
        userMap.put(cs2.getId(), cs2);
    }

    @Override
    public boolean exists(String id) {
        return userMap.containsKey(id);
    }

    @Override
    public User getUser(String id) {
        return userMap.get(id);
    }

    @Override
    public boolean matches(String id, String pwd) {
        return Optional.ofNullable(getUser(id))
                .map(user -> user.getPwd().equals(pwd))
                .orElse(false);
    }

    // 수정사항 있을 때 써야지
    @Override
    public void save(User user) {
        userMap.put(user.getId(), user);
    }

}
