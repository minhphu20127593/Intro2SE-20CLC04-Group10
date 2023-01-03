package com.group10.TalesOfWonder.chapter;

import com.group10.TalesOfWonder.entity.Chapter;
import com.group10.TalesOfWonder.entity.Comic;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChapterRepository extends CrudRepository<Chapter, Integer> {
    @Query("SELECT u from Chapter u Where u.comic.id = :id ORDER BY name DESC " )
    public List<Chapter> findByComicID(@Param("id") Integer id);
    @Query(value = "SELECT  TOP (:num) * from Chapters u Where u.comic_id = :id ORDER BY name DESC" ,nativeQuery = true)
    public List<Chapter> findByComicIDLimit(@Param("id") Integer id,@Param("num") Integer numLimit);
}
