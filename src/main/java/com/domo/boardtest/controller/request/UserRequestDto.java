package com.domo.boardtest.controller.request;

import com.domo.boardtest.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {

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
