package com.group10.TalesOfWonder.comic;

import com.group10.TalesOfWonder.FileUploadUtil;
import com.group10.TalesOfWonder.entity.Category;
import com.group10.TalesOfWonder.entity.Comic;
import com.group10.TalesOfWonder.entity.User;
import com.group10.TalesOfWonder.security.QAUserDetails;
import com.group10.TalesOfWonder.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
public class ComicController {
    @Autowired
    public ComicService comicService;
    @Autowired
    public UserService userService;
    @GetMapping("/comic/new_comic")
    public String newComic(Model model,@AuthenticationPrincipal QAUserDetails loggedUser) {
        List<Category> categories = comicService.findAllCategory();
        model.addAttribute("categories",categories);
        String email = loggedUser.getUsername();
        User user = userService.getByEmail(email);
        Comic comic = new Comic();
        comic.setCreator(user);
        model.addAttribute("comic",comic);
        return "new_comic";
    }

    @PostMapping("/comic/save")
    public String saveComic(@ModelAttribute Comic comic, RedirectAttributes redirectAttributes,@RequestParam("image")MultipartFile multipartFile) throws IOException {

        if (multipartFile.isEmpty() == false) {
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            comic.setPoster(fileName);
            Comic comicSave = comicService.save(comic);
            String uploadDir = "comic-poster/" + comicSave.getId();
            FileUploadUtil.cleanDir(uploadDir);
            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

        } else {
            if (comic.getPoster()==null || comic.getPoster().isEmpty()) comic.setPoster(null);
            comicService.save(comic);
        }
        redirectAttributes.addFlashAttribute("message","User Comic successfully");
        return "redirect:/comics";
    }
}
