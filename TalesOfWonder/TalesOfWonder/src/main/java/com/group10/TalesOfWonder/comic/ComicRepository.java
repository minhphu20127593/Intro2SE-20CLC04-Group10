package com.group10.TalesOfWonder.comic;

import com.group10.TalesOfWonder.entity.Comic;
import com.group10.TalesOfWonder.entity.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComicRepository extends PagingAndSortingRepository<Comic,Integer> {
    @Query("SELECT u from Comic u Where u.creator.email = :email")
    public List<Comic> getAllComicByEmail(@Param("email") String email);
}