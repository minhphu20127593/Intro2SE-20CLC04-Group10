package com.group10.TalesOfWonder.user;

import com.group10.TalesOfWonder.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User,Integer> {
    public User getUserByEmail(String email);
    @Query(nativeQuery = true,value = "SELECT * FROM users as u inner join users_comics_follow as c on u.id = c.user_id where c.comic_id = ?1")
    public List<User> findUserFollowAComic(int comicID);
}
