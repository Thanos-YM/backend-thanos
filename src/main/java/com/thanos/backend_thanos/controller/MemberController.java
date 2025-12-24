package com.thanos.backend_thanos.controller;

import com.thanos.backend_thanos.common.BaseResponse;
import com.thanos.backend_thanos.entity.Member;
import com.thanos.backend_thanos.repository.MemberRepository;
import com.thanos.backend_thanos.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {
}
