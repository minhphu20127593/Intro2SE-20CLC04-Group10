package com.group10.TalesOfWonder.image;

import com.group10.TalesOfWonder.entity.Chapter;
import com.group10.TalesOfWonder.entity.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {
    @Autowired
    public ImageRepository imageRepository;
    public void saveAllImage(List<Image>images) {
        imageRepository.saveAll(images);
    }
    public List<Image> getAllImageOfChapter(Chapter chapter) {
        return imageRepository.findAllByChapter(chapter);
    }
}
