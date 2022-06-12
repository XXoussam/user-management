package com.fsb.user_project;

import com.fsb.user_project.user.User;
import com.fsb.user_project.user.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testAddNew() {
        User user = new User();
        user.setEmail("oussama.saoudi@gmail.com");
        user.setPassword("ouss123456");
        user.setFirstName("oussama");
        user.setLastnName("saoudi");

        User savedUser = userRepository.save(user);
        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    public void testListAll() {
        Iterable<User> users = userRepository.findAll();
        Assertions.assertThat(users).hasSizeGreaterThan(0);

        for (User user : users
        ) {
            System.out.println(user);

        }
        ;
    }

    @Test
    public void testUpdate() {
        Integer userId = 1;
        Optional<User> userOptional = userRepository.findById(userId);
        User user = userOptional.get();
        user.setPassword("hello2000");
        userRepository.save(user);

        User updatedUser = userRepository.findById(userId).get();
        Assertions.assertThat(updatedUser.getPassword()).isEqualTo("hello2000");
    }

    @Test
    public void testGet() {
        Integer userId = 2;
        Optional<User> userOptional = userRepository.findById(userId);
        User user = userOptional.get();

        Assertions.assertThat(userOptional).isPresent();
        System.out.println(userOptional.get());
    }

    @Test
    public void tetsDelete() {
        Integer userId = 3;
        userRepository.deleteById(userId);

        Optional<User> userOptional = userRepository.findById(userId);
        Assertions.assertThat(userOptional).isNotPresent();
    }
}
