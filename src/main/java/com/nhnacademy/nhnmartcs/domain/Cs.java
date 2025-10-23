package com.nhnacademy.nhnmartcs.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
public class Cs implements User {
    String id;
    String pwd;
    String name;
}
