package com.group10.TalesOfWonder.user;

import com.group10.TalesOfWonder.entity.FormCreator;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormCreatorRepository extends CrudRepository<FormCreator,Integer> {
}
