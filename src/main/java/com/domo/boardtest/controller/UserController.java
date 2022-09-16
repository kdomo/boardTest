package com.domo.boardtest.controller;

import com.domo.boardtest.common.ResultDto;
import com.domo.boardtest.controller.request.UserSignupDto;
import com.domo.boardtest.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;


    @PostMapping("/signup")
    public ResponseEntity<ResultDto> signup(UserSignupDto requestDto){
        log.info("UserController : signup");
        userService.signup(requestDto);
        return new ResponseEntity<>(ResultDto.builder().code(1).msg("회원가입 성공").body(null).build(), HttpStatus.OK);
    }


}
