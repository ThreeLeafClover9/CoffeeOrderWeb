package com.codestates.CoffeeOrderWeb.member.repository;

import com.codestates.CoffeeOrderWeb.member.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MemberRepository extends CrudRepository<Member, Long> {
    Optional<Member> findByEmail(String email);

    Page<Member> findAll(Pageable pageable);
}
