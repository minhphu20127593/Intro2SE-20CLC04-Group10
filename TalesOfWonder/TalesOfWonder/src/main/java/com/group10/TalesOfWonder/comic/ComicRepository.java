package com.group10.TalesOfWonder.comic;

import com.group10.TalesOfWonder.entity.Comic;
import com.group10.TalesOfWonder.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComicRepository extends PagingAndSortingRepository<Comic,Integer> {
    @Query("SELECT u from Comic u Where u.creator.email = :email")
    public List<Comic> getAllComicByEmail(@Param("email") String email);
    @Query("SELECT u FROM Comic u Where u.name Like %?1%")
    public Page<Comic> findAllByKeyWord(String keyword, Pageable pageable);
    @Query("SELECT u FROM Comic u Where u.name Like %?1% and u.creator.id = ?2")
    public Page<Comic> findAllByKeyWordOfUser(String keyword, int id, Pageable pageable);
    @Query("SELECT u FROM Comic u Where u.creator.id = ?1")
    public Page<Comic> findAllByUser(int id, Pageable pageable);
}