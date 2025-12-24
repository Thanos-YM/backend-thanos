package com.thanos.backend_thanos.repository;

import com.thanos.backend_thanos.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MemberRepository extends JpaRepository<Member, UUID> {
}
