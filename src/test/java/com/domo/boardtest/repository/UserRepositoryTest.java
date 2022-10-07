package com.domo.boardtest.repository;

import com.domo.boardtest.controller.request.UserRequestDto;
import com.domo.boardtest.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;


    @Test
    @DisplayName("user가 DB에 저장이 잘 되는지 확인")
    void saveUser(){
        //given
        User user = new UserRequestDto("userId01","userName01","password01").toEntity();

        //when
        User saveUser = userRepository.save(user);
        System.out.println(saveUser.getId());
        System.out.println(saveUser.getUserId());
        System.out.println(saveUser.getUserName());
        System.out.println(saveUser.getUserPassword());
        //then
        assertThat(saveUser).isSameAs(user);
        assertThat(saveUser.getUserId()).isEqualTo(user.getUserId());
        assertThat(saveUser.getUserName()).isEqualTo(user.getUserName());
        assertThat(saveUser.getUserPassword()).isEqualTo(user.getUserPassword());
        assertThat(saveUser.getId()).isNotNull();
        assertThat(userRepository.count()).isEqualTo(1);
    }

    @Test
    @DisplayName("userList가 DB에서 잘 불러와지는지 확인")
    void findAll(){
        //given
        User user = new UserRequestDto("userId02","userName02","password02").toEntity();
        userRepository.save(user);

        //when
        List<User> userList = userRepository.findAll();

        //then
        User getUser = userList.get(userList.size()-1);

        assertThat(getUser.getUserId()).isEqualTo(user.getUserId());
        assertThat(getUser.getUserName()).isEqualTo(user.getUserName());
        assertThat(getUser.getUserPassword()).isEqualTo(user.getUserPassword());
        assertThat(getUser.getId()).isNotNull();
    }


    @Test
    @DisplayName("userId로 중복확인이 잘 되는지 확인")
    void existsByUserId() {
        //given
        User user = new UserRequestDto("userId01","userName01","password01").toEntity();
        userRepository.save(user);

        //when
        boolean exists = userRepository.existsByUserId(user.getUserId());

        assertThat(exists).isEqualTo(true);
        assertThat(false).isEqualTo(userRepository.existsByUserId("userId02"));
    }
}