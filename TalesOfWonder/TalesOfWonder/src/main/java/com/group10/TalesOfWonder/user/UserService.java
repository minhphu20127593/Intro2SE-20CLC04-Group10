package com.group10.TalesOfWonder.user;

import com.group10.TalesOfWonder.entity.Comic;
import com.group10.TalesOfWonder.entity.FormCreator;
import com.group10.TalesOfWonder.entity.Role;
import com.group10.TalesOfWonder.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private FormCreatorRepository formCreatorRepository;
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
            if (user.getPassword() == null || user.getPassword().isEmpty())
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

    public FormCreator saveFormCreator(FormCreator formCreator) {
        formCreator.setDateRegister(new Date());
        return formCreatorRepository.save(formCreator);
    }

    public List<FormCreator> getAllFormCreator() {
        return (List<FormCreator>) formCreatorRepository.findAll();
    }

    public void removeFormCreator(int formCreatorId) {
        formCreatorRepository.deleteById(formCreatorId);
    }

    public void changeRoleToCreator(int formCreatorId) {
        FormCreator formCreator = formCreatorRepository.findById(formCreatorId).get();
        User user = formCreator.getUser();
        Role creator = roleRepository.getRoleByName("Creator");
        user.setRole(creator);
        userRepository.save(user);
    }
}
