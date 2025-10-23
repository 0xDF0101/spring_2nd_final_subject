package com.nhnacademy.nhnmartcs.repository;

import com.nhnacademy.nhnmartcs.domain.Complain;

public interface ComplainRepository {

    boolean exists(long complainId);

    Complain register(Complain complain);

    Complain getComplain(long complainId);
}
