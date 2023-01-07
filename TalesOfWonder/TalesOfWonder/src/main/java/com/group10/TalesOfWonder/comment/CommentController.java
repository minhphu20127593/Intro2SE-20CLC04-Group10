package com.group10.TalesOfWonder.comment;

import com.group10.TalesOfWonder.entity.Comic;
import com.group10.TalesOfWonder.entity.Comment;
import com.group10.TalesOfWonder.entity.User;
import com.group10.TalesOfWonder.security.QAUserDetails;
import com.group10.TalesOfWonder.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
public class CommentController {
    @Autowired
    public CommentService commentService;
    @Autowired
    public UserService userService;
    @PostMapping("comment/save")
    public String saveComment(@AuthenticationPrincipal QAUserDetails loggedUser, @ModelAttribute("comment") Comment comment, RedirectAttributes redirectAttributes) {
        if (loggedUser == null)
            return "not login yet";
        String email = loggedUser.getUsername();
        User user = userService.getByEmail(email);
        comment.setUser(user);
        Comment commentSave = commentService.saveComment(comment);
        return "success";
    }
}
