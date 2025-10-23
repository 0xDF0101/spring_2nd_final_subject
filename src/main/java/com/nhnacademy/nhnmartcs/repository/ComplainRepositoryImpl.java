package com.nhnacademy.nhnmartcs.repository;

import com.nhnacademy.nhnmartcs.domain.Complain;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ComplainRepositoryImpl implements ComplainRepository{

    private final Map<Long, Complain> complainMap = new HashMap<>();

    public ComplainRepositoryImpl() {

    }

    @Override
    public boolean exists(long complainId) {
        return complainMap.containsKey(complainId);
    }

    @Override
    public Complain register(Complain complain) {
        complainMap.put(complain.getComplainId(), complain);
        return complain; // 굳이 반환을 해야하나?
    }

    @Override
    public Complain getComplain(long complainId) {
        return complainMap.get(complainId);
    }
}
