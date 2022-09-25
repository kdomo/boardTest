package com.domo.boardtest.controller.response;

import com.domo.boardtest.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {
    @NotNull
    private Long id;

    @NotNull
    private String userId;

    @NotNull
    private String userName;

    @NotNull
    private String userPassword;

    public User toEntity(){
        return User.builder()
                .userId(userId)
                .userName(userName)
                .userPassword(userPassword)
                .build();
    }
}
