package com.group10.TalesOfWonder.user;

import com.group10.TalesOfWonder.entity.Chapter;
import com.group10.TalesOfWonder.entity.Message;
import com.group10.TalesOfWonder.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

@Service
public class MessageService {
    @Autowired
    public MessageRepository messageRepository;
    @Autowired
    public UserRepository userRepository;
    public List<Message> getAllMessageOfUser(User user) {
        return messageRepository.findMessageOfAUser(user.getId());
    }
    public boolean sendMessageWhenNewChapter(Chapter chapter) {
        List<User> users = userRepository.findUserFollowAComic(chapter.getComic().getId());
        Message message = new Message();
        message.setDateSend(new Date());
        message.setTitle(chapter.getName() + " - "+chapter.getComic().getName()+" is coming");
        message.setContent("/readChapter?comicID=" + chapter.getComic().getId()+"&chapterID="+chapter.getId());
        message.setUsers(new HashSet<>(users));
        messageRepository.save(message);
        return true;
    }
}
