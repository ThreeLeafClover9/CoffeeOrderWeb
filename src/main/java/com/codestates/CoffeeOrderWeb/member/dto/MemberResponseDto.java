package com.codestates.CoffeeOrderWeb.member.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberResponseDto {
    private long memberId;
    private String email;
    private String name;
    private String phone;
}


