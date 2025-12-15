package com.thanos.backend_thanos.controller;

import com.thanos.backend_thanos.common.BaseResponse;
import com.thanos.backend_thanos.dto.request.LoginRequest;
import com.thanos.backend_thanos.dto.request.SignUpRequest;
import com.thanos.backend_thanos.dto.response.AuthResponse;
import com.thanos.backend_thanos.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Tag(name = "Auth", description = "인증 API")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "로그인", description = "이메일과 비밀번호로 로그인하여 토큰을 발급받습니다.")
    @PostMapping("/login")
    public ResponseEntity<BaseResponse<AuthResponse>> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(BaseResponse.ok(authService.login(request)));
    }

    @Operation(summary = "회원가입", description = "이메일과 비밀번호로 회원가입합니다.")
    @PostMapping("/signup")
    public ResponseEntity<BaseResponse<Map<String, Object>>> signUp(@RequestBody SignUpRequest request) {
        return ResponseEntity.ok(BaseResponse.ok(authService.signUp(request)));
    }
}