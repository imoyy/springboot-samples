package com.hxc.springbootweek02.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private long id;
    private String name;
    private String gender;
    private LocalDate birthday;
    private String address;
    private Phone phone;
}