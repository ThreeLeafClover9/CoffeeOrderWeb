package com.codestates.CoffeeOrderWeb.member.repository;

import com.codestates.CoffeeOrderWeb.member.entity.Member;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends CrudRepository<Member, Long> {
    Optional<Member> findByEmail(String email);

    @Override
    List<Member> findAll();
}
