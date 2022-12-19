package com.group10.TalesOfWonder.user;

import com.group10.TalesOfWonder.entity.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
class RoleRepositoryTest {
    @Autowired
    private RoleRepository roleRepository;
    @Test
    public void testCreateRole() {
        Role roleAdmin = new Role("Admin","quản lý user, comic, chapter");
        Role roleCreator = new Role("Creator","quản lý comic, chapter");
        Role roleReader = new Role("Reader","đọc truyện, lưu truyện, comment, báo lỗi,...");
        Iterable<Role> roles = roleRepository.saveAll(List.of(roleAdmin,roleCreator,roleReader));
        roles.forEach(role-> System.out.printf(String.valueOf(role)));
    }
}