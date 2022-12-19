package com.group10.TalesOfWonder.comic;

import com.group10.TalesOfWonder.entity.Comic;
import com.group10.TalesOfWonder.entity.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ComicRepository extends PagingAndSortingRepository<Comic,Integer> {
    @Query("SELECT u from User u Where u.email = :email")
    public Comic getAllComicByEmail(@Param("email") String email);
}