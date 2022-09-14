package com.domo.boardtest.service;

import com.domo.boardtest.controller.request.UserSignupDto;

public interface UserService {
    void signup(UserSignupDto dto);

    boolean checkValidNickname(String nickname);
}
