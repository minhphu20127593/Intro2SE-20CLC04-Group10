package com.group10.TalesOfWonder.comic;

import com.group10.TalesOfWonder.entity.Category;
import com.group10.TalesOfWonder.entity.Comic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ComicService {
    @Autowired
    public CategoryRepository categoryRepository;
    @Autowired
    public ComicRepository comicRepository;
    public List<Category> findAllCategory() {
        List<Category> categories = (List<Category>) categoryRepository.findAll();
        return categories;
    }
    public Comic getComicByID(Integer id) {
        return comicRepository.findById(id).get();
    }
    public Comic save(Comic comic) {
        if (comic.getId() == null) {
            comic.setCountView(0);
            comic.setDatePost(new Date());
            comic.setLastModified(new Date());
        } else {
            Comic comicInDB = comicRepository.findById(comic.getId()).get();
            if (comic.getPoster()==null || comic.getPoster().isEmpty())
                comic.setPoster(comicInDB.getPoster());
            comic.setCountView(comicInDB.getCountView());
            comic.setDatePost(comicInDB.getDatePost());
            comic.setLastModified(new Date());
            comic.setCreator(comicInDB.getCreator());
        }

        return comicRepository.save(comic);
    }
    public List<Comic> findAllComicByEmail(String email) {
        return (List<Comic>) comicRepository.getAllComicByEmail(email);
    }
}
