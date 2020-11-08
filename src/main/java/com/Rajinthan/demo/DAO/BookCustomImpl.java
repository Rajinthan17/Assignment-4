package com.Rajinthan.demo.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.Rajinthan.demo.Entity.Books;



@Repository
public class BookCustomImpl implements BookCustomRepo {
	
	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public int GetMaxBookId() {
		Query query = new Query();
        query.with(Sort.by(Sort.Direction.DESC, "id"));
      query.limit(1);
      
      Books maxObject = mongoTemplate.findOne(query, Books.class);
      if (maxObject == null) {
                  return 0;
      }
   return maxObject.getId();
	}

}
