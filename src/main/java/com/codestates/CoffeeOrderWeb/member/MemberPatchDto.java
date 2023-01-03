package com.codestates.CoffeeOrderWeb.member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberPatchDto {
    private long memberId;
    private String name;
    private String phone;
}
