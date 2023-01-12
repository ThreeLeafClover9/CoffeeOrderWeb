package com.codestates.CoffeeOrderWeb.member.controller;

import com.codestates.CoffeeOrderWeb.member.dto.MemberPatchDto;
import com.codestates.CoffeeOrderWeb.member.dto.MemberPostDto;
import com.codestates.CoffeeOrderWeb.member.dto.MemberResponseDto;
import com.codestates.CoffeeOrderWeb.member.entity.Member;
import com.codestates.CoffeeOrderWeb.member.mapper.MemberMapper;
import com.codestates.CoffeeOrderWeb.member.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/v1/members")
@Validated
public class MemberController {
    private final MemberService memberService;
    private final MemberMapper memberMapper;

    public MemberController(MemberService memberService, MemberMapper memberMapper) {
        this.memberService = memberService;
        this.memberMapper = memberMapper;
    }

    @PostMapping
    public ResponseEntity postMember(@Valid @RequestBody MemberPostDto memberPostDto) {
        Member member = memberMapper.memberPostDtoToMember(memberPostDto);
        Member response = memberService.createMember(member);
        MemberResponseDto memberResponseDto = memberMapper.memberToMemberResponseDto(response);
        return new ResponseEntity<>(memberResponseDto, HttpStatus.CREATED);
    }

    @PatchMapping("/{member-id}")
    public ResponseEntity patchMember(@PathVariable("member-id") @Positive long memberId,
                                      @Valid @RequestBody MemberPatchDto memberPatchDto) {
        memberPatchDto.setMemberId(memberId);
        Member member = memberMapper.memberPatchDtoToMember(memberPatchDto);
        Member response = memberService.updateMember(member);
        MemberResponseDto memberResponseDto = memberMapper.memberToMemberResponseDto(response);
        return new ResponseEntity<>(memberResponseDto, HttpStatus.OK);
    }

    @GetMapping("/{member-id}")
    public ResponseEntity getMember(@PathVariable("member-id") @Positive long memberId) {
        Member response = memberService.findMember(memberId);
        MemberResponseDto memberResponseDto = memberMapper.memberToMemberResponseDto(response);
        return new ResponseEntity<>(memberResponseDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getMembers() {
        List<Member> response = memberService.findMembers();
        List<MemberResponseDto> memberResponseDtos = memberMapper.membersToMemberResponseDtos(response);
        return new ResponseEntity<>(memberResponseDtos, HttpStatus.OK);
    }

    @DeleteMapping("/{member-id}")
    public ResponseEntity deleteMember(@PathVariable("member-id") @Positive long memberId) {
        memberService.deleteMember(memberId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
