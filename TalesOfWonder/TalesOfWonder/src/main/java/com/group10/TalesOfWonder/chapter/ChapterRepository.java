package com.group10.TalesOfWonder.chapter;

import com.group10.TalesOfWonder.entity.Chapter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChapterRepository extends CrudRepository<Chapter, Integer> {
}
