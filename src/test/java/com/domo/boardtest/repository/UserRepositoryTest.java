package com.domo.boardtest.repository;

import com.domo.boardtest.controller.request.UserSignupDto;
import com.domo.boardtest.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;

import static org.assertj.core.api.Assertions.*;


@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;


    @Test
    @DisplayName("user가 DB에 저장이 잘 되는지 확인")
    void saveUser(){
        //given
        User user = new UserSignupDto("user01","password01").toEntity();

        //when
        User saveUser = userRepository.save(user);

        //then
        assertThat(saveUser).isSameAs(user);
        assertThat(saveUser.getNickname()).isEqualTo(user.getNickname());
        assertThat(saveUser.getPassword()).isEqualTo(user.getPassword());
        assertThat(saveUser.getId()).isNotNull();
        assertThat(userRepository.count()).isEqualTo(1);
    }

    @Test
    @DisplayName("userList가 DB에서 잘 불러와지는지 확인")
    void findAll(){
        //given
        User user = new UserSignupDto("user01","password01").toEntity();
        userRepository.save(user);

        //when
        List<User> userList = userRepository.findAll();

        //then
        assertThat(userList.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("nickname으로 DB에서 조회가 잘 되는지 확인")
    void findByNickname(){
        //given
        User user03 = new UserSignupDto("user03","password03").toEntity();
        userRepository.save(user03);

        //when
        User user02 = userRepository.findByNickname("user03").get();

        //then
        assertThat(user02.getNickname()).isEqualTo("user03");
        assertThat(user02.getPassword()).isEqualTo("password03");
    }

    @Test
    void existsByNickname(){
        User user04 = new UserSignupDto("user04","password04").toEntity();
        userRepository.save(user04);

        //when
        boolean exists = userRepository.existsByNickname("user04");

        //then
        assertThat(exists).isTrue();
    }
}