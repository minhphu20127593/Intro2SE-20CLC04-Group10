package com.group10.TalesOfWonder.user;

import com.group10.TalesOfWonder.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User,Integer> {
    public User getUserByEmail(String email);
}
