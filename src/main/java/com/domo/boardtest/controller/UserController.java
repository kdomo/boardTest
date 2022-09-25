package com.domo.boardtest.controller;

import com.domo.boardtest.common.ResultDto;
import com.domo.boardtest.controller.request.UserRequestDto;
import com.domo.boardtest.controller.response.UserResponseDto;
import com.domo.boardtest.service.UserService;
import com.domo.boardtest.utils.SessionUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;
//    private final HttpSession httpSession;

    @GetMapping("/userInfo")
    public ResponseEntity<ResultDto> userInfo(HttpSession httpSession) {
        Long id = SessionUtil.getLoginId(httpSession);
        userService.getUserInfo(id);
        return new ResponseEntity<>(ResultDto.builder().code(1).msg("hi").build(), HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<ResultDto> signup(UserRequestDto requestDto) {
        log.info("UserController : signup");
        userService.signup(requestDto);
        return new ResponseEntity<>(ResultDto.builder().code(1).msg("회원가입 성공").body(null).build(), HttpStatus.OK);
    }

    //2.로그인
    @PostMapping("/login")
    public ResponseEntity<ResultDto> login(HttpSession session, @RequestBody String  userId, @RequestBody String userPassword) {
        log.info("userController : login");
        UserResponseDto dto = userService.login(userId, userPassword);
        SessionUtil.setLoginId(session, dto.getId());
        return new ResponseEntity<>(ResultDto.builder().code(1).msg("로그인 성공").body(null).build(), HttpStatus.OK);
    }

    //3.로그아웃
    @PostMapping("/logout")
    public ResponseEntity<ResultDto> logout(@RequestBody String  userId) {
        log.info("userController : logout");
        httpSession.removeAttribute(userId);
        return new ResponseEntity<>(ResultDto.builder().code(1).msg("로그아웃 성공").body(null).build(), HttpStatus.OK);
    }
}
