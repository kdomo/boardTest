package com.domo.boardtest.service;

import com.domo.boardtest.controller.request.UserSignupDto;
import com.domo.boardtest.domain.User;
import com.domo.boardtest.exception.AlreadyExistNicknameException;
import com.domo.boardtest.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    @Override
    public void signup(UserSignupDto signupDto) {
        if(checkValidNickname(signupDto.getNickname())){
            throw new AlreadyExistNicknameException("이미 존재하는 닉네임 입니다");
        }

        String encodedPassword = passwordEncoder.encode(signupDto.getPassword());

        User user = User.builder()
                .nickname(signupDto.getNickname())
                .password(encodedPassword)
                .build();

        userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean checkValidNickname(String nickname) {

        return userRepository.existsByNickname(nickname);

    }
}
