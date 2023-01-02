package com.codestates.CoffeeOrderWeb.member;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/members")
public class MemberController {
    @PostMapping
    public ResponseEntity postMember(@RequestParam("email") String email,
                                     @RequestParam("name") String name,
                                     @RequestParam("phone") String phone) {
        Map<String, String> member = new HashMap<>();
        member.put("email", email);
        member.put("name", name);
        member.put("phone", phone);
        return new ResponseEntity<>(member, HttpStatus.CREATED);
    }

    @PatchMapping("/{member-id}")
    public ResponseEntity patchMember(@PathVariable("member-id") long memberId,
                                      @RequestParam("phone") String phone) {
        Map<String, Object> member = new HashMap<>();
        member.put("memberId", memberId);
        member.put("email", "hgd@gmail.com");
        member.put("name", "홍길동");
        member.put("phone", phone);
        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    @GetMapping("/{member-id}")
    public ResponseEntity getMember(@PathVariable("member-id") long memberId) {
        System.out.println("memberId = " + memberId);
        // not implementation
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getMembers() {
        System.out.println("get Members");
        // not implementation
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{member-id}")
    public ResponseEntity deleteMember(@PathVariable("member-id") long memberId) {
        // No need business logic
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
