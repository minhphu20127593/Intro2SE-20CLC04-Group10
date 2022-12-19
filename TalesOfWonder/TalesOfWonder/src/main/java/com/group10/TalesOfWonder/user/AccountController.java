package com.group10.TalesOfWonder.user;

import com.group10.TalesOfWonder.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountController {
    @GetMapping("/signup")
    public String signUp(Model model) {
        User user = new User();
        model.addAttribute("user",user);
        return "sign_up";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "log_in";
    }
}
