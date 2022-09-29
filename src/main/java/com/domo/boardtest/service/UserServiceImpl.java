package com.domo.boardtest.service;

import com.domo.boardtest.controller.request.UserRequestDto;
import com.domo.boardtest.controller.response.UserResponseDto;
import com.domo.boardtest.domain.User;
import com.domo.boardtest.exception.AlreadyExistNicknameException;
import com.domo.boardtest.repository.UserRepository;
import com.domo.boardtest.utils.SessionUtil;
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
        User user = optionalUser.get();
        return UserResponseDto.builder()
                .id(user.getId())
                .userId(user.getUserId())
                .userName(user.getUserName())
                .userPassword(user.getUserPassword())
                .build();
    }

    @Override
    public UserResponseDto getUserInfo(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if(!optionalUser.isPresent()){
            throw new RuntimeException();
        }
        User user = optionalUser.get();

        return UserResponseDto.builder()
                .id(user.getId())
                .userId(user.getUserId())
                .userName(user.getUserName())
                .userPassword(user.getUserPassword())
                .build();
    }


}
