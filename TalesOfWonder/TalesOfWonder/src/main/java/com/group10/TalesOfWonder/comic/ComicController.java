package com.group10.TalesOfWonder.comic;

import com.group10.TalesOfWonder.FileUploadUtil;
import com.group10.TalesOfWonder.chapter.ChapterRepository;
import com.group10.TalesOfWonder.chapter.ChapterService;
import com.group10.TalesOfWonder.entity.Category;
import com.group10.TalesOfWonder.entity.Chapter;
import com.group10.TalesOfWonder.entity.Comic;
import com.group10.TalesOfWonder.entity.User;
import com.group10.TalesOfWonder.security.QAUserDetails;
import com.group10.TalesOfWonder.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
public class ComicController {
    @Autowired
    public ComicService comicService;
    @Autowired
    public UserService userService;
    @Autowired
    public ChapterService chapterService;
    @GetMapping("/comic/new_comic")
    public String newComic(Model model,@AuthenticationPrincipal QAUserDetails loggedUser) {
        List<Category> categories = comicService.findAllCategory();
        model.addAttribute("categories",categories);
        String email = loggedUser.getUsername();
        User user = userService.getByEmail(email);
        Comic comic = new Comic();
        comic.setCreator(user);
        comic.setEnable(false);
        model.addAttribute("comic",comic);
        return "new_comic";
    }
    @GetMapping("/comic/listAll")
    public String getAllOfUser(Model model,@AuthenticationPrincipal QAUserDetails loggedUser) {
        return listAllComic(model,loggedUser,1,"lastModified","DESC",null);
    }
    @GetMapping("/comic/listAll/{pageNum}")
    public String listAllComic(Model model,@AuthenticationPrincipal QAUserDetails loggedUser,@PathVariable(name = "pageNum") int pageNum, @Param("sortField") String sortField, @Param("sortDir")
            String sortDir, @Param("keyword") String keyword) {
        sortField = sortDir == null ? "lastModified" : sortField;
        sortDir = sortDir == null ? "DESC" : sortDir;
        String email = loggedUser.getUsername();
        User user = userService.getByEmail(email);
        Page<Comic> page = comicService.listByPageOfUser(pageNum,sortField,sortDir,keyword,user);
        List<Comic> comicsList = page.getContent();
        long startCount = (pageNum - 1)* ComicService.pageSizeSmall + 1;
        long endCount = startCount + ComicService.pageSizeSmall - 1;
        if (endCount > page.getTotalElements())
            endCount = page.getTotalElements();
        String opsortDir = sortDir.equals("asc")?"des":"asc";
        System.out.println(opsortDir);
        System.out.println("Pagenum = " + pageNum);
        System.out.println("Total elements = " + page.getTotalElements());
        System.out.println("Total pages = " + page.getTotalPages());
        model.addAttribute("startCount",startCount);
        model.addAttribute("endCount",endCount);
        model.addAttribute("currentPage",pageNum);
        model.addAttribute("totalItems",page.getTotalElements());
        model.addAttribute("totalPages",page.getTotalPages());
        model.addAttribute("comics",comicsList);
        model.addAttribute("sortField",sortField);
        model.addAttribute("sortDir",sortDir);
        model.addAttribute("opsortDir",opsortDir);
        model.addAttribute("keyword",keyword);
        return "quanlytruyen";
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
        return "redirect:/comic/listAll";
    }
    @GetMapping("/comic/changestatus/{id}")
    public String changeStatus(@PathVariable(name = "id") Integer id,Model model) {
        Comic comic = comicService.getComicByID(id);
        if (comic.getStatus().equals("In progress")) {
            comic.setStatus("Done");
        } else
            comic.setStatus("In progress");
        comicService.save(comic);
        return "redirect:/comic/listAll";
    }
    @GetMapping("/")
    public String loadMainPage(Model model) {
        Page<Comic> page = comicService.listByPage(1,"lastModified","des",null);
        List<Comic> comicListLatest = page.getContent();
        Map<Integer,List<Chapter>> chaptersLatest = chapterService.getListChapterOfComics(comicListLatest);
        page = comicService.listByPage(1,"countView","des",null);
        List<Comic> comicsMostView = page.getContent();
        Map<Integer,List<Chapter>> chaptersMostView = chapterService.getListChapterOfComics(comicsMostView);
        long startCount = (1 - 1)* ComicService.pageSize + 1;
        long endCount = startCount + ComicService.pageSize - 1;
        if (endCount > page.getTotalElements())
            endCount = page.getTotalElements();
        System.out.println("Pagenum = " + 1);
        System.out.println("Total elements = " + page.getTotalElements());
        System.out.println("Total pages = " + page.getTotalPages());
        model.addAttribute("listChaptersLatest",chaptersLatest);
        model.addAttribute("listChaptersMostView",chaptersMostView);
        model.addAttribute("startCount",startCount);
        model.addAttribute("endCount",endCount);
        model.addAttribute("currentPage",1);
        model.addAttribute("totalItems",page.getTotalElements());
        model.addAttribute("totalPages",page.getTotalPages());
        model.addAttribute("listComicsLatest",comicListLatest);
        model.addAttribute("listComicsMostView",comicsMostView);
        return "index";
    }
    @GetMapping("/admin/approveComic")
    public String approveComic(Model model) {
        List<Comic> comics = comicService.findAllComicWaitForApprove();
        model.addAttribute("comics",comics);
        return "approveComic";
    }

    @GetMapping("/comic/activecomic/{comicId}")
    public String activeComic(Model model,@PathVariable("comicId") int comicId) {
        Comic comic = comicService.getComicByID(comicId);
        comic.setEnable(true);
        comicService.save(comic);
        return "redirect:/admin/approveComic";
    }
    @GetMapping("/comic/deleteComic/{comicId}")
    public String deleteComic(@PathVariable("comicId") int comicId) {
        boolean success = comicService.deleteComic(comicId);
        return  "redirect:/admin/approveComic";
    }
//    @GetMapping("/{pageNum}")
//    public String listByPage(@PathVariable(name = "pageNum") int pageNum, @Param("sortField") String sortField, @Param("sortDir")
//            String sortDir, Model model, @Param("keyword") String keyword) {
//        Page<Comic> page = comicService.listByPage(pageNum,sortField,sortDir,keyword);
//        List<Comic> postsList = page.getContent();
//        long startCount = (pageNum - 1)* ComicService.pageSize + 1;
//        long endCount = startCount + ComicService.pageSize - 1;
//        if (endCount > page.getTotalElements())
//            endCount = page.getTotalElements();
//        String opsortDir = sortDir.equals("asc")?"des":"asc";
//        System.out.println(opsortDir);
//        System.out.println("Pagenum = " + pageNum);
//        System.out.println("Total elements = " + page.getTotalElements());
//        System.out.println("Total pages = " + page.getTotalPages());
//        model.addAttribute("startCount",startCount);
//        model.addAttribute("endCount",endCount);
//        model.addAttribute("currentPage",pageNum);
//        model.addAttribute("totalItems",page.getTotalElements());
//        model.addAttribute("totalPages",page.getTotalPages());
//        model.addAttribute("listPosts",postsList);
//        model.addAttribute("sortField",sortField);
//        model.addAttribute("sortDir",sortDir);
//        model.addAttribute("opsortDir",opsortDir);
//        model.addAttribute("keyword",keyword);
//        return "index";
//    }
}
