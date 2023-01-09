package com.group10.TalesOfWonder.comment;

import com.group10.TalesOfWonder.entity.Chapter;
import com.group10.TalesOfWonder.entity.Comic;
import com.group10.TalesOfWonder.entity.Comment;
import com.group10.TalesOfWonder.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends PagingAndSortingRepository<Comment,Integer> {
    @Query("SELECT u FROM Comment u Where u.user  = ?1")
    public Page<Comment> findAllByUser(User user, Pageable pageable);
    @Query("SELECT u FROM Comment u Where u.chapter.id= ?1")
    public Page<Comment> findAllByChapter(int chapterID, Pageable pageable);
    @Query("SELECT u FROM Comment u Where u.comic= ?1")
    public Page<Comment> findAllByComic(Comic comic, Pageable pageable);
    @Query("SELECT u FROM Comment u Where u.comic = ?1 and commentParent.commentParent = null")
    public Page<Comment> findAllByComicNoParent(Comic comic, Pageable pageable);
    @Query("SELECT u FROM Comment u Where u.user = ?1  ORDER BY dateComment DESC")
    public List<Comment> findAllByUser(User user);
}
