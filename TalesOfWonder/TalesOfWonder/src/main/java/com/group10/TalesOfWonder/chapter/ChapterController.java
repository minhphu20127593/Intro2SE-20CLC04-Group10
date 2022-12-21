package com.group10.TalesOfWonder.chapter;

import com.group10.TalesOfWonder.FileUploadUtil;
import com.group10.TalesOfWonder.comic.ComicService;
import com.group10.TalesOfWonder.entity.*;
import com.group10.TalesOfWonder.image.ImageService;
import com.group10.TalesOfWonder.security.QAUserDetails;
import com.group10.TalesOfWonder.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ChapterController {
    @Autowired
    public ChapterService chapterService;
    @Autowired
    public UserService userService;
    @Autowired
    public ComicService comicService;
    @Autowired
    public ImageService imageService;
    @GetMapping("/chapter/new_chapter")
    public String newComic(Model model,@AuthenticationPrincipal QAUserDetails loggedUser) {
        String email = loggedUser.getUsername();
        User user = userService.getByEmail(email);
        Chapter chapter = new Chapter();
        List<Comic> comics = comicService.findAllComicByEmail(email);
        Comic comic = new Comic();
        chapter.setComic(comic);
        chapter.setEnable(false);
        model.addAttribute("comics",comics);
        model.addAttribute("chapter",chapter);
        return "new_chapter";
    }

    @PostMapping("/chapter/save")
    public String saveComic(@ModelAttribute Chapter chapter, RedirectAttributes redirectAttributes, @RequestParam("files") MultipartFile[] multipartFiles) throws IOException {
        int count = 0;
        Comic comic = comicService.getComicByID(chapter.getComic().getId());
        chapter.setComic(comic);
        Chapter chapterSave = chapterService.save(chapter);
        List<Image> images = new ArrayList<>();
        for (MultipartFile multipartFile: multipartFiles)
        {
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            Image image = new Image(count,fileName,chapterSave);
            images.add(image);
            String uploadDir = "comic-poster/" + chapterSave.getId();
            FileUploadUtil.cleanDir(uploadDir);
            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
            count++;
        }
        imageService.saveAllImage(images);
        redirectAttributes.addFlashAttribute("message","User Comic successfully");
        return "redirect:/comics";
    }
}
