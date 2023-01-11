package com.group10.TalesOfWonder.user;

import com.group10.TalesOfWonder.entity.Message;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends CrudRepository<Message,Integer> {
    @Query(nativeQuery = true,value = "SELECT TOP (10) * FROM message as u inner join messages_users as m  on u.id = m.message_id where m.user_id = ?1 ORDER BY date_send DESC")
    public List<Message> findMessageOfAUser(int userID);
}
