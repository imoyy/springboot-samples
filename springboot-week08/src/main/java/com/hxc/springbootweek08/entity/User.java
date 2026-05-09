package com.hxc.springbootweek08.entity;

import lombok.Data;

@Data
public class User {

    private String name;

    private Integer age;

    private String email;

    private Address address;
}
