package com.group10.TalesOfWonder.user;

import com.group10.TalesOfWonder.entity.Role;
import com.group10.TalesOfWonder.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
class UserRepositoryTest {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Test
    public void testCreateUser() {
        Role role = roleRepository.getRoleByName("Reader");
        User user = new User("dmmurri@gmail.com","D Mu rí","123456",true,role);
        user = userRepository.save(user);
        System.out.println(user);
    }
}