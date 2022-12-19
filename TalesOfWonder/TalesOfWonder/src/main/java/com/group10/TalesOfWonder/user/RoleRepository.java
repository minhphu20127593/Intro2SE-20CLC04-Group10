package com.group10.TalesOfWonder.user;

import com.group10.TalesOfWonder.entity.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role,Integer> {
    @Query("SELECT u from Role u Where u.name = :name")
    public Role getRoleByName(@Param("name") String name);
}
