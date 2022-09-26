package com.domo.boardtest.service;

import com.domo.boardtest.controller.request.UserRequestDto;
import com.domo.boardtest.controller.response.UserResponseDto;

import javax.servlet.http.HttpSession;

public interface UserService {
    void signup(UserRequestDto dto);

    boolean checkValidUserId(String userId);

    UserResponseDto login(String userId, String userPassword);

    UserResponseDto getUserInfo(Long id);
}
