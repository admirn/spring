package com.admirnurkovic.bookStore.domain.dto;

import jakarta.validation.constraints.Email;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {
    @NonNull
    @Email
    private String email;

    @NonNull
    private String password;
}
