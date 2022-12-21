package com.group10.TalesOfWonder.comic;

import com.group10.TalesOfWonder.entity.Comic;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
class ComicRepositoryTest {
    @Autowired
    private ComicRepository comicRepository;
    @Test
    public void getAllComic() {
        List<Comic> comics = (List<Comic>) comicRepository.getAllComicByEmail("pttluong20@clc.fitus.edu.vn");
        for (Comic comic: comics)
            System.out.println(comic);
    }
}