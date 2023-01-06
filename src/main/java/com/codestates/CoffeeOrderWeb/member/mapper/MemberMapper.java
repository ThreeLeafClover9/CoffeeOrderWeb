package com.codestates.CoffeeOrderWeb.member.mapper;

import com.codestates.CoffeeOrderWeb.member.dto.MemberPatchDto;
import com.codestates.CoffeeOrderWeb.member.dto.MemberPostDto;
import com.codestates.CoffeeOrderWeb.member.dto.MemberResponseDto;
import com.codestates.CoffeeOrderWeb.member.entity.Member;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    Member memberPostDtoToMember(MemberPostDto memberPostDto);

    Member memberPatchDtoToMember(MemberPatchDto memberPatchDto);

    MemberResponseDto memberToMemberResponseDto(Member member);

    List<MemberResponseDto> membersToMemberResponseDtos(List<Member> members);
}
