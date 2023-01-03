package com.group10.TalesOfWonder.image;

import com.group10.TalesOfWonder.entity.Chapter;
import com.group10.TalesOfWonder.entity.Image;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends CrudRepository<Image,Image> {
    @Query("SELECT u from Image u Where u.chapter = :chapter")
    public List<Image> findAllByChapter(@Param("chapter") Chapter chapter);
}
