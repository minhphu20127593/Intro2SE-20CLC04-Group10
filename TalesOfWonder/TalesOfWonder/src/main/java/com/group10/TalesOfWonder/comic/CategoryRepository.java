package com.group10.TalesOfWonder.comic;


import com.group10.TalesOfWonder.entity.Category;
import com.group10.TalesOfWonder.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category,Integer> {
}
