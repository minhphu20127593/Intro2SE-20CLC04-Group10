package com.group10.TalesOfWonder.chapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ChapterController {
    @Autowired
    public ChapterService chapterService;
    @PostMapping("/chapter/new_chapter")
    public String newChapter(Model model) {
        return "new_chapter";
    }
}
