package com.nhnacademy.nhnmartcs.repository;

import com.nhnacademy.nhnmartcs.domain.Role;
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
        User user1 = new User("eugene", "qwer", "Eugene", new ArrayList<>(), Role.CUSTOMER);
        User user2 = new User("suin", "qwer", "Suin", new ArrayList<>(), Role.CUSTOMER);
        User cs1 = new User("illoong", "qwer", "Illoong", new ArrayList<>(), Role.ADMIN);
        User cs2 = new User("gyujin", "qwer", "gyujin", new ArrayList<>(), Role.ADMIN);

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
