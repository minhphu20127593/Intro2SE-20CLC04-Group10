package com.group10.TalesOfWonder.comic;

import com.group10.TalesOfWonder.entity.Comic;
import com.group10.TalesOfWonder.entity.User;
import com.group10.TalesOfWonder.security.QAUserDetails;
import com.group10.TalesOfWonder.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ComicRestController {
    @Autowired
    public ComicService comicService;
    @Autowired
    public UserService userService;

    @GetMapping("/readComic/{comicID}/vote/{star}")
    public String voteComic(@PathVariable("comicID") int comicID, @PathVariable("star") int star, @AuthenticationPrincipal QAUserDetails loggedUser) {
        if (loggedUser == null)
            return "Not login yet";
        String email = loggedUser.getUsername();
        User user = userService.getByEmail(email);
        Comic comic = comicService.getComicByID(comicID);
        boolean isVoteYet = comicService.voteAComic(comic, user, star);
        if (isVoteYet)
            return "You have voted this comic before. Update vote";
        return "Your vote have been saved";
    }

    @GetMapping("/readComic/{comicID}/follow")
    public String voteComic(@PathVariable("comicID") int comicID, @AuthenticationPrincipal QAUserDetails loggedUser) {
        if (loggedUser == null)
            return "Not login yet";
        String email = loggedUser.getUsername();
        User user = userService.getByEmail(email);
        Comic comic = comicService.getComicByID(comicID);
        boolean isFollowYet = comicService.followAComic(comic, user);
        if (isFollowYet)
            return "You have followed this comic yet";
        else
            return "The comic has saved as your favorite comics";
    }
}
