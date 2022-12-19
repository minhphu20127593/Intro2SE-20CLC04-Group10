package com.group10.TalesOfWonder.user;

import com.group10.TalesOfWonder.FileUploadUtil;
import com.group10.TalesOfWonder.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/users/save")
    public String saveUser(User user, RedirectAttributes redirectAttributes) throws IOException {

        if (user.getPhotos()==null || user.getPhotos().isEmpty()) user.setPhotos(null);

        userService.save(user);
        redirectAttributes.addFlashAttribute("message","User create successfully");
        return "redirect:/users";
    }
}
