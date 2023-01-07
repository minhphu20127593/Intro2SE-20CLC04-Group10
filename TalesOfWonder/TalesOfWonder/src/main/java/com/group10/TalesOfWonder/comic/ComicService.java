package com.group10.TalesOfWonder.comic;

import com.group10.TalesOfWonder.entity.Category;
import com.group10.TalesOfWonder.entity.Comic;
import com.group10.TalesOfWonder.entity.User;
import com.group10.TalesOfWonder.entity.Vote;
import com.group10.TalesOfWonder.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ComicService {
    public static Integer pageSize = 16;
    public static Integer pageSizeSmall = 4;
    @Autowired
    public CategoryRepository categoryRepository;
    @Autowired
    public ComicRepository comicRepository;
    @Autowired
    public VoteRepository voteRepository;
    @Autowired
    public UserService userService;
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

    public Page<Comic> listByPage(int pageNum, String sortField, String sortDir, String keyword) {
        Sort sort = Sort.by(sortField);
        sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
        Pageable pageable = PageRequest.of(pageNum - 1,pageSize,sort);
        if (keyword != null)
            return comicRepository.findAllByKeyWord(keyword,pageable);
        Page<Comic> posts = comicRepository.findAll(pageable);

        return posts;
    }

    public Page<Comic> listByPageOfUser(int pageNum, String sortField, String sortDir, String keyword, User user) {
        Sort sort = Sort.by(sortField);
        sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
        Pageable pageable = PageRequest.of(pageNum - 1,pageSizeSmall,sort);
        if (keyword != null)
            return comicRepository.findAllByKeyWordOfUser(keyword,user.getId(),pageable);
        Page<Comic> posts = comicRepository.findAllByUser(user.getId(),pageable);

        return posts;
    }

    public List<Comic> findAllComicWaitForApprove() {
        return comicRepository.findAllComicWaitForApprove();
    }
    public boolean deleteComic(int comicID) {
        Comic comic = comicRepository.findById(comicID).get();
        if (comic == null)
                return false;
        comicRepository.deleteById(comicID);
        return true;
    }
    public int getAVGStar(Comic comic) {
        int count = voteRepository.countVote(comic);
        if (count == 0)
            return 0;
        return voteRepository.getAVG(comic);
    }
    public void increaseCountView(Comic comic) {
        comic.increaseContView();
        comicRepository.save(comic);
    }
    public Boolean voteAComic(Comic comic,User user,int star) {
        Vote vote = voteRepository.getVote(user,comic);
        if (vote!=null){
            vote.setStart(star);
            voteRepository.save(vote);
            return true;
        } else {
            vote = new Vote(comic,user,star);
            voteRepository.save(vote);
            return  false;
        }
    }
    public boolean followAComic(Comic comic, User user) {
        boolean check = user.checkIfFollowComic(comic);
        if (check)
            return true;
        user.followComic(comic);
        userService.save(user);
        return false;
    }
}
