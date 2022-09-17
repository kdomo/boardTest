package com.domo.boardtest.controller.request;

import com.domo.boardtest.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserSignupDto {

    @NotNull
    private String nickname;

    @NotNull
    private String password;

    public User toEntity(){
        return User.builder()
                .nickname(nickname)
                .password(password)
                .build();
    }
}
