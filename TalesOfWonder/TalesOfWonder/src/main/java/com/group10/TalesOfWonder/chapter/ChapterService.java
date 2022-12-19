package com.group10.TalesOfWonder.chapter;

import com.group10.TalesOfWonder.entity.Chapter;
import com.group10.TalesOfWonder.entity.Comic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ChapterService {
    @Autowired
    public ChapterRepository chapterRepository;

    public Chapter save(Chapter chapter) {
        if (chapter.getId() == null) {
            chapter.setCountView(0);
            chapter.setDateModified(new Date());
        } else {
            Chapter comicInDB = chapterRepository.findById(chapter.getId()).get();
            chapter.setCountView(comicInDB.getCountView());
            chapter.setDateModified(new Date());
            chapter.setComic(comicInDB.getComic());
        }

        return chapterRepository.save(chapter);
    }
}
