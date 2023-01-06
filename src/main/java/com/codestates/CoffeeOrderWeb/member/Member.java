package com.codestates.CoffeeOrderWeb.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Member {
    private long memberId;
    private String email;
    private String name;
    private String phone;
}
