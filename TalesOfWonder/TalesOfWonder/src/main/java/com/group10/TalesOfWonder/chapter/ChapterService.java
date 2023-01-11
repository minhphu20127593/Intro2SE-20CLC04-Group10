package com.group10.TalesOfWonder.chapter;

import com.group10.TalesOfWonder.entity.Chapter;
import com.group10.TalesOfWonder.entity.Comic;
import com.group10.TalesOfWonder.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

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
    public Chapter findChapterByID(int id) {
        return chapterRepository.findById(id).get();
    }
    public List<Chapter> getListChapterOfAComic(Comic comic) {
        return chapterRepository.findByComicID(comic.getId());
    }
    public Map<Integer,List<Chapter>> getListChapterOfComics(List<Comic> comics) {
        Map<Integer,List<Chapter>> chapters = new HashMap<>();
        for (Comic comic:comics) {
            List<Chapter> chapterComic = chapterRepository.findByComicIDLimit(comic.getId(),3);
            chapters.put(comic.getId(),chapterComic);
        }
        return chapters;
    }

    public Page<Chapter> listByPageOfChapter(int pageNum, String sortField, String sortDir, String keyword, Comic comic) {
        Sort sort = Sort.by(sortField);
        sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
        Pageable pageable = PageRequest.of(pageNum - 1,10,sort);
        if (keyword != null)
            return chapterRepository.findAllByKeyWord(keyword,comic,pageable);
        Page<Chapter> chapters = chapterRepository.findAllByComic(comic.getId(),pageable);

        return chapters;
    }

    public void increaseChapterCountView(Chapter currentChapter) {
        currentChapter.increaseCountView();
        chapterRepository.save(currentChapter);
    }
}
