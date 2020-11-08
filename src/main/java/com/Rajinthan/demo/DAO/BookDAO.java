package com.Rajinthan.demo.DAO;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.Rajinthan.demo.Entity.Books;



@Repository
public interface BookDAO extends MongoRepository<Books,Integer> {
	
	
	@Query("{$or: [ { 'title': { $regex: ?0, $options: 'i' } }, { 'author':{ $regex: ?0 , $options: 'i'} },{ 'isbNumber':{ $regex: ?0, $options: 'i' } },{ 'language':{ $regex: ?0, $options: 'i' } },{ 'genere':{ $regex: ?0, $options: 'i' } } ]}")
	Page<Books> findByTitleAndAuthorContaining(Pageable pageable, String searchString);
}
