package com.group10.TalesOfWonder.user;

import com.group10.TalesOfWonder.FileUploadUtil;
import com.group10.TalesOfWonder.chapter.ChapterService;
import com.group10.TalesOfWonder.comic.ComicService;
import com.group10.TalesOfWonder.comment.CommentService;
import com.group10.TalesOfWonder.entity.*;
import com.group10.TalesOfWonder.security.QAUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ComicService comicService;
    @Autowired
    private ChapterService chapterService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private MessageService messageService;

    @PostMapping("/users/save")
    public String saveUser(User user, RedirectAttributes redirectAttributes) throws IOException {

        if (user.getPhotos()==null || user.getPhotos().isEmpty()) user.setPhotos(null);

        userService.save(user);
        redirectAttributes.addFlashAttribute("message","User create successfully");
        return "redirect:/";
    }

    @GetMapping("/dashboard")
    public String viewDashboard(@AuthenticationPrincipal QAUserDetails loggedUser, Model model) {
        String email = loggedUser.getUsername();
        User user = userService.getByEmail(email);
        List<Comment> commentList = commentService.getAllCommentOfUser(user);
        Map<Integer,List<Chapter>> latestChapters = chapterService.getListChapterOfComics(new ArrayList<>(user.getComicsFollow()));
        Map<Integer,Chapter> latestChapter = new HashMap<>();
        for (int id: latestChapters.keySet()) {
            latestChapter.put(id,latestChapters.get(id).size()==0?null:latestChapters.get(id).get(0));
        }
        FormCreator formCreator = new FormCreator(user,"");
        List<Comic> comics = comicService.findAllComicByEmail(user.getEmail());
        Map<Integer,List<Chapter>> latestChaptersUpload = chapterService.getListChapterOfComics(comics);
        for (int id: latestChaptersUpload.keySet()) {
            latestChapter.put(id,latestChaptersUpload.get(id).size()==0?null:latestChaptersUpload.get(id).get(0));
        }
        List<Message> messages = messageService.getAllMessageOfUser(user);
        model.addAttribute("messages",messages);
        model.addAttribute("comics",comics);
        model.addAttribute("formCreator",formCreator);
        model.addAttribute("user",user);
        model.addAttribute("comments",commentList);
        model.addAttribute("followComic",user.getComicsFollow());
        model.addAttribute("latestChaptersUpload",latestChapter);
        model.addAttribute("latestChapter",latestChapter);
        return "account_info";
    }
    @GetMapping("/dashboard/unfollow/{comicId}")
    public String unFollow(Model model,@AuthenticationPrincipal QAUserDetails loggedUser,@PathVariable("comicId") int comicId) {
        Comic comic = comicService.getComicByID(comicId);
        String email = loggedUser.getUsername();
        User user = userService.getByEmail(email);
        user.unFollowComic(comic);
        return viewDashboard(loggedUser,model);
    }
    @PostMapping("/dashboard/update")
    public String updateProfile(Model model,@AuthenticationPrincipal QAUserDetails loggedUser, User user,@RequestParam("image")MultipartFile multipartFile) throws IOException {
        if (multipartFile.isEmpty() == false) {
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            user.setPhotos(fileName);
            User saveuser = userService.save(user);
            String uploadDir = "user-photos/" + saveuser.getId();
            FileUploadUtil.cleanDir(uploadDir);
            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        } else {
            User saveuser = userService.save(user);
        }
        return viewDashboard(loggedUser,model);
    }
    @GetMapping("/admin/approveCreator")
    public String viewformCreator(Model model) {
        List<FormCreator> formCreators = userService.getAllFormCreator();
        model.addAttribute("formCreators",formCreators);
        return "approveCreator";
    }
    @PostMapping("/dashboard/formCreator")
    public String saveformCreator(Model model,@AuthenticationPrincipal QAUserDetails loggedUser,FormCreator formCreator) {
        userService.saveFormCreator(formCreator);
        return viewDashboard(loggedUser,model);
    }
    @GetMapping("/admin/approveCreator/{formCreatorId}")
    public String approveCreator(Model model,@PathVariable("formCreatorId") int formCreatorId, @Param("approve") boolean approve) {
        if (approve) {
            userService.changeRoleToCreator(formCreatorId);
        }
        userService.removeFormCreator(formCreatorId);
        return viewformCreator(model);
    }
}
