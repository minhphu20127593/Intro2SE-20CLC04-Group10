package com.group10.TalesOfWonder.comment;

import com.group10.TalesOfWonder.entity.Chapter;
import com.group10.TalesOfWonder.entity.Comic;
import com.group10.TalesOfWonder.entity.Comment;
import com.group10.TalesOfWonder.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    public CommentRepository commentRepository;
    public static Integer pageSizeSmall = 10;
    public Page<Comment> getAllCommentOfComic(Comic comic, int pageNum) {
        Sort sort = Sort.by("dateComment").descending();
        Pageable pageable = PageRequest.of(pageNum - 1,pageSizeSmall,sort);
        return commentRepository.findAllByComic(comic,pageable);
    }

    public Page<Comment> getAllCommentOfChapter(Chapter chapter, int pageNum) {
        Sort sort = Sort.by("dateComment").descending();
        Pageable pageable = PageRequest.of(pageNum - 1,pageSizeSmall,sort);
        return commentRepository.findAllByChapter(chapter.getId(),pageable);
    }
    public Comment saveComment(Comment comment) {
        comment.setDateComment(new Date());
        return commentRepository.save(comment);
    }

    public List<Comment> getAllCommentOfUser(User user) {
        return commentRepository.findAllByUser(user);
    }
}
