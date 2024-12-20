package com.umc.workbook.config.security;

import com.umc.workbook.domain.enums.Role;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CustomUserInfoDTO {
    private Long memberId;
    private String email;
    private String password;
    private String nickName;
    private String role;
}
