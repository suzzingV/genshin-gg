package com.suzzingv.genshingg.domain.Member.repository;

import com.suzzingv.genshingg.domain.Member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
