package com.domo.boardtest.dto;

import com.domo.boardtest.domain.User;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserJoinRequestDto {
    private String nickname;
    private String password;


    public User toEntity(){
        return User.builder()
                .nickname(nickname)
                .password(password)
                .build();
    }
}
