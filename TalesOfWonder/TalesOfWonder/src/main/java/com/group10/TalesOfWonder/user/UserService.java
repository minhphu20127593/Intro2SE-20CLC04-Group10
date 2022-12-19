package com.group10.TalesOfWonder.user;

import com.group10.TalesOfWonder.entity.Role;
import com.group10.TalesOfWonder.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public User save(User user) {
        if (user.getRole() == null) {
            Role reader = roleRepository.getRoleByName("Reader");
            user.setRole(reader);
        }
        if (user.getId() == null) {
            user.setPassword(encodePassword(user));
            user.setRegisterDate(new Date());
        } else {
            User oldUser = userRepository.findById(user.getId()).get();
            if (user.getPassword().isEmpty())
                user.setPassword(oldUser.getPassword());
            else
                user.setPassword(encodePassword(user));
            if (user.getPhotos()==null || user.getPhotos().isEmpty())
                user.setPhotos(oldUser.getPhotos());
            user.setRegisterDate(oldUser.getRegisterDate());
        }

        return userRepository.save(user);
    }
    public String encodePassword(User user) {
        return passwordEncoder.encode(user.getPassword());
    }

    public User getByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }
}
