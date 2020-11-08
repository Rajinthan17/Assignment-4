package com.Rajinthan.demo.DAO;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.Rajinthan.demo.Entity.ERole;
import com.Rajinthan.demo.Entity.Role;





@Repository
public interface RoleRepository extends MongoRepository<Role, String> {
  Optional<Role> findByName(ERole name);
}