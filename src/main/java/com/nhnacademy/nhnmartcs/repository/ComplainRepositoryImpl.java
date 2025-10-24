package com.nhnacademy.nhnmartcs.repository;

import com.nhnacademy.nhnmartcs.domain.Category;
import com.nhnacademy.nhnmartcs.domain.Complain;
import com.nhnacademy.nhnmartcs.domain.FileAttachment;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ComplainRepositoryImpl implements ComplainRepository{

    private final Map<Long, Complain> complainMap = new HashMap<>();
    private static long idCounter = 1;

    public ComplainRepositoryImpl() {

    }

    @Override
    public boolean exists(long complainId) {
        return complainMap.containsKey(complainId);
    }

    @Override
    public long register(String title, String content, Category category, String customerId,
                         List<FileAttachment> files) {

        Complain complain = new Complain(idCounter, title, content, category, LocalDateTime.now(), customerId, files, false, null, null, null);

        complainMap.put(complain.getComplainId(), complain);
        return idCounter++; // 굳이 반환을 해야하나?
    }

    @Override
    public Complain getComplain(long complainId) {
        return complainMap.get(complainId);
    }

    @Override
    public List<Complain> getCustomerComplain(List<Long> complainIds) {

        List<Complain> complains = new ArrayList<>();
        for(long id : complainIds) {
            complains.add(complainMap.get(id));
        }

        return complains;
    }

    // 맵을 통째로 반환하는게 좋은 방법인지는 모르겠음
    @Override
    public Map<Long, Complain> getAllComplain() {
        return complainMap;
    }
}
