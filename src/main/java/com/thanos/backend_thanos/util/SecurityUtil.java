package com.thanos.backend_thanos.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import java.util.UUID;

public class SecurityUtil {

    public static UUID getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !(authentication.getPrincipal() instanceof Jwt jwt)) {
            throw new RuntimeException("인증 정보가 없습니다.");
        }

        // Supabase JWT 토큰의 'sub' 클레임에 user_id가 들어가 있음
        return UUID.fromString(jwt.getClaim("sub"));
    }
}
