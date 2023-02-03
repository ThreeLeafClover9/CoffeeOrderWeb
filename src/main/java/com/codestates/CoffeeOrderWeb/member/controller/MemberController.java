package com.codestates.CoffeeOrderWeb.member.controller;

import com.codestates.CoffeeOrderWeb.member.dto.MemberPatchDto;
import com.codestates.CoffeeOrderWeb.member.dto.MemberPostDto;
import com.codestates.CoffeeOrderWeb.member.dto.MemberResponseDto;
import com.codestates.CoffeeOrderWeb.member.entity.Member;
import com.codestates.CoffeeOrderWeb.member.mapper.MemberMapper;
import com.codestates.CoffeeOrderWeb.member.service.MemberService;
import com.codestates.CoffeeOrderWeb.response.MultiResponseDto;
import com.codestates.CoffeeOrderWeb.response.SingleResponseDto;
import com.codestates.CoffeeOrderWeb.utils.UriCreator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/members")
@Validated
@RequiredArgsConstructor
public class MemberController {
    private final static String MEMBER_DEFAULT_PATH = "/v1/members";
    private final MemberService memberService;
    private final MemberMapper memberMapper;

    @PostMapping
    public ResponseEntity postMember(@Valid @RequestBody MemberPostDto memberPostDto) {
        Member member = memberMapper.memberPostDtoToMember(memberPostDto);
        Member createdMember = memberService.createMember(member);
        long memberId = createdMember.getMemberId();
        URI location = UriCreator.createUri(MEMBER_DEFAULT_PATH, memberId);
        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/{member-id}")
    public ResponseEntity patchMember(@PathVariable("member-id") @Positive long memberId,
                                      @Valid @RequestBody MemberPatchDto memberPatchDto) {
        memberPatchDto.setMemberId(memberId);
        Member member = memberMapper.memberPatchDtoToMember(memberPatchDto);
        Member updatedMember = memberService.updateMember(member);
        MemberResponseDto memberResponseDto = memberMapper.memberToMemberResponseDto(updatedMember);
        SingleResponseDto<MemberResponseDto> singleResponseDto = new SingleResponseDto<>(memberResponseDto);
        return new ResponseEntity<>(singleResponseDto, HttpStatus.OK);
    }

    @GetMapping("/{member-id}")
    public ResponseEntity getMember(@PathVariable("member-id") @Positive long memberId) {
        Member foundMember = memberService.findMember(memberId);
        MemberResponseDto memberResponseDto = memberMapper.memberToMemberResponseDto(foundMember);
        SingleResponseDto<MemberResponseDto> singleResponseDto = new SingleResponseDto<>(memberResponseDto);
        return new ResponseEntity<>(singleResponseDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getMembers(@RequestParam @Positive int page,
                                     @RequestParam @Positive int size) {
        Page<Member> foundMemberPage = memberService.findMembers(page, size);
        List<Member> foundMembers = foundMemberPage.getContent();
        List<MemberResponseDto> memberResponseDtos = memberMapper.membersToMemberResponseDtos(foundMembers);
        MultiResponseDto<MemberResponseDto> multiResponseDto = new MultiResponseDto<>(memberResponseDtos, foundMemberPage);
        return new ResponseEntity<>(multiResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{member-id}")
    public ResponseEntity deleteMember(@PathVariable("member-id") @Positive long memberId) {
        memberService.deleteMember(memberId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
