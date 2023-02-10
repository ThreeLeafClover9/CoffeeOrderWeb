package com.codestates.CoffeeOrderWeb.member.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Table
public class Member {
    @Id
    private long memberId;
    private String email;
    private String name;
    private String phone;
}
