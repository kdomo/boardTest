package com.domo.boardtest.service;

import com.domo.boardtest.controller.request.UserRequestDto;
import com.domo.boardtest.controller.response.UserResponseDto;
import com.domo.boardtest.domain.User;
import com.domo.boardtest.exception.AlreadyExistNicknameException;
import com.domo.boardtest.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

//    private final PasswordEncoder passwordEncoder;

    @Override
    public void signup(UserRequestDto signupDto) {
        if(checkValidUserId(signupDto.getUserId())){
            throw new AlreadyExistNicknameException("이미 존재하는 아이디 입니다");
        }

//        String encodedPassword = passwordEncoder.encode(signupDto.getPassword());
        String encodedPassword = signupDto.getUserPassword();

        User user = User.builder()
                .userId(signupDto.getUserId())
                .userName(signupDto.getUserName())
                .userPassword(encodedPassword)
                .build();

        userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean checkValidUserId(String userId) {
        return userRepository.existsByUserId(userId);
    }


    @Override
    public UserResponseDto login(String userId, String userPassword) {
        Optional<User> optionalUser = userRepository.findByUserIdAndUserPassword(userId, userPassword);
        if(!optionalUser.isPresent()) {
            //Exception 수정해야 함
            throw new RuntimeException();
        }

    }
}
