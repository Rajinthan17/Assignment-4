package com.Rajinthan.demo.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.beans.support.SortDefinition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Rajinthan.demo.DAO.BookCustomRepo;
import com.Rajinthan.demo.DAO.BookDAO;
import com.Rajinthan.demo.Entity.Books;

@Service
public class BookService {
	
	@Autowired
	BookCustomRepo bookCustomRepo;
	
	@Autowired
	BookDAO bookDAO;

	public ResponseEntity<Books> createBook(Books book) {
		try {
			int id = bookCustomRepo.GetMaxBookId()+1;
			Books newBook = bookDAO.save(new Books (id,book.getTitle(), book.getAuthor(), book.getUrl(), book.getIsbNumber(),book.getPrice(),book.getLanguage(),book.getGenere()));
			return new ResponseEntity<>(newBook,HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
		}
	}

	public ResponseEntity<List<Books>> getAllBooks() {
		try {
			List<Books> newBooks = bookDAO.findAll();
			if(newBooks.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(newBooks,HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
		}
	}

	public ResponseEntity<Books> updateBook(int id, Books book) {
		Optional <Books> bookData = bookDAO.findById(id);
		
		if(bookData.isPresent()) {
			Books _book = bookData.get();
			_book.setTitle(book.getTitle());
			_book.setAuthor(book.getAuthor());
			_book.setIsbNumber(book.getIsbNumber());
			_book.setUrl(book.getUrl());
			_book.setPrice(book.getPrice());
			_book.setLanguage(book.getLanguage());
			_book.setGenere(book.getGenere());
			
			return new ResponseEntity<>(bookDAO.save(_book),HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<Books> deleteBook(int id) {
		try {
			bookDAO.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<Map<String, Object>> searchedBooks(String searched, String priceSort,int pageNo, int pageSize) {
			
			Map<String, Object> response = new HashMap<>();
        	Sort sort = priceSort.equals("DESC")? Sort.by(Sort.Direction.DESC,"price"):(Sort.by(Sort.Direction.ASC,"price"));
	        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
	        Page<Books> page = bookDAO.findByTitleAndAuthorContaining(pageable, searched);
	        response.put("data", page.getContent());
	        response.put("Total_No_Of_Pages", page.getTotalPages());
	        response.put("Total_No_Of_Elements", page.getTotalElements());
	        response.put("Current page no", page.getNumber());
			return new ResponseEntity<>(response, HttpStatus.OK);
		}

	public ResponseEntity<Books> getBook(int id) {
		try {
			Optional<Books> bookData = bookDAO.findById(id);
			if(bookData.isPresent()) {
				return new ResponseEntity<>(bookData.get(),HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<Map<String, Object>> getAllBooks(String priceSort,int pageNo, int pageSize) {
		try {
            Map<String, Object> response = new HashMap<>();
            	Sort sort = priceSort.equals("DESC")? Sort.by(Sort.Direction.DESC,"price"):(Sort.by(Sort.Direction.ASC,"price"));
            Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
            Page<Books> page = bookDAO.findAll(pageable);
            response.put("data", page.getContent());
            response.put("Total_No_Of_Pages", page.getTotalPages());
            response.put("Total_No_Of_Elements", page.getTotalElements());
            response.put("Current page no", page.getNumber());
            
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch(Exception e) {
        	return new ResponseEntity<>(null,HttpStatus.SERVICE_UNAVAILABLE);
        }
	}
}
