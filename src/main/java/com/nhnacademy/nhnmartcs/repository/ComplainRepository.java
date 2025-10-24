package com.nhnacademy.nhnmartcs.repository;

import com.nhnacademy.nhnmartcs.domain.Category;
import com.nhnacademy.nhnmartcs.domain.Complain;
import com.nhnacademy.nhnmartcs.domain.FileAttachment;

import java.util.List;
import java.util.Map;

public interface ComplainRepository {

    boolean exists(long complainId);

    long register(String title, String content, Category category, String customerId,
                         List<FileAttachment> files);

    Complain getComplain(long complainId);

    List<Complain> getCustomerComplain(List<Long> complainIds);

    Map<Long, Complain> getAllComplain();

}
