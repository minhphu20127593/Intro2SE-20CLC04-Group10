package com.group10.TalesOfWonder.image;

import com.group10.TalesOfWonder.entity.Image;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends CrudRepository<Image,Image> {
}
