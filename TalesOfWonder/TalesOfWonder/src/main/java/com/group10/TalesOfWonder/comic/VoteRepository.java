package com.group10.TalesOfWonder.comic;

import com.group10.TalesOfWonder.entity.Comic;
import com.group10.TalesOfWonder.entity.Role;
import com.group10.TalesOfWonder.entity.User;
import com.group10.TalesOfWonder.entity.Vote;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends CrudRepository<Vote,Integer> {
    @Query("SELECT u from Vote u Where u.user = ?1 and u.comic = ?2")
    public Vote getVote(User user,Comic comic);
    @Query("SELECT AVG(u.start) from Vote u Where u.comic = ?1")
    public int getAVG(Comic comic);
    @Query("SELECT COUNT(u.start) from Vote u Where u.comic = ?1")
    public int countVote(Comic comic);
}
