package com.group10.TalesOfWonder.chapter;

import com.group10.TalesOfWonder.FileUploadUtil;
import com.group10.TalesOfWonder.comic.ComicService;
import com.group10.TalesOfWonder.comment.CommentService;
import com.group10.TalesOfWonder.entity.*;
import com.group10.TalesOfWonder.image.ImageService;
import com.group10.TalesOfWonder.security.QAUserDetails;
import com.group10.TalesOfWonder.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ChapterController {
    @Autowired
    public CommentService commentService;
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
        String uploadDir = "comic-image/" + chapterSave.getId();
        FileUploadUtil.cleanDir(uploadDir);
        for (MultipartFile multipartFile: multipartFiles)
        {
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            Image image = new Image(count,fileName,chapterSave);
            images.add(image);
            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
            count++;
        }
        imageService.saveAllImage(images);
        redirectAttributes.addFlashAttribute("message","User Comic successfully");
        return "redirect:/chapters";
    }
    @GetMapping("/readChapter")
    public String readChapter(Model model,@Param("comicID") int comicID,@Param("chapterID") int chapterID,@Param("commentPage") String commentPage) {
        Comic comic = comicService.getComicByID(comicID);
        List<Chapter> chapters = chapterService.getListChapterOfAComic(comic);
        Chapter currentChapter = chapterService.findChapterByID(chapterID);
        int curindex = chapters.indexOf(currentChapter);
        Chapter prev = null,next = null;
        if (curindex!=0)
            prev = chapters.get(curindex-1);
        if (curindex<chapters.size()-1)
            next = chapters.get(curindex+1);
        Comment comment = new Comment();
        comment.setComic(comic);
        comment.setChapter(currentChapter);
        int commentpage = 1;
        if (commentPage != null)
            commentpage = Integer.parseInt(commentPage);

        List<Image> images = imageService.getAllImageOfChapter(currentChapter);
        Page<Comment> page = commentService.getAllCommentOfChapter(currentChapter,commentpage);
        List<Comment> comments = page.getContent();
        long startCount = (1 - 1)* ComicService.pageSize + 1;
        long endCount = startCount + ComicService.pageSize - 1;
        if (endCount > page.getTotalElements())
            endCount = page.getTotalElements();
        model.addAttribute("comments",comments);
        model.addAttribute("startCount",startCount);
        model.addAttribute("endCount",endCount);
        model.addAttribute("currentPage",commentpage);
        model.addAttribute("totalItems",page.getTotalElements());
        model.addAttribute("totalPages",page.getTotalPages());
        model.addAttribute("comment",comment);
        model.addAttribute("images",images);
        model.addAttribute("chapters",chapters);
        model.addAttribute("prevChapter",prev);
        model.addAttribute("nextChapter",next);
        model.addAttribute("currentIndex",curindex);
        model.addAttribute("currentChapter",currentChapter);
        model.addAttribute("comic",comic);
        return "readChapter";
    }

}
