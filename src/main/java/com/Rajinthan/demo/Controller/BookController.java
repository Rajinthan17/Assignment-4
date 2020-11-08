package com.Rajinthan.demo.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Rajinthan.demo.Entity.Books;
import com.Rajinthan.demo.Service.BookService;



@RestController
@CrossOrigin("*")
@RequestMapping (value = "/book")
public class BookController {
	
	@Autowired
	BookService bookService;
	
	@PostMapping
	public ResponseEntity<Books> createBook(@RequestBody Books book){
		return bookService.createBook(book);
	}
	
	@GetMapping
	public ResponseEntity<List<Books>> getAllBooks(){
		return bookService.getAllBooks();
	}
	
	@GetMapping (value = "/page")
	public ResponseEntity <Map<String, Object>> getAllBooks(
			@RequestParam(name = "sort",defaultValue = "DESC") String priceSort,
			@RequestParam(name = "pageNo",defaultValue = "0") int pageNo,
			@RequestParam(name = "pageSize",defaultValue = "5") int pageSize){
		return bookService.getAllBooks(priceSort,pageNo,pageSize);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Books> getBook(@PathVariable("id") int id){
		return bookService.getBook(id);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Books> updateBook(@PathVariable("id") int id,@RequestBody Books book ){
		return bookService.updateBook(id ,book);
	}
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Books> deleteBook(@PathVariable("id") int id){
		return bookService.deleteBook(id);
	}
	
	@GetMapping(value = "/serached")
	public ResponseEntity<Map<String,Object>> getSerchedBook(
			@RequestParam(name = "serched",defaultValue = "null") String searched,
			@RequestParam(name = "sort",defaultValue = "DESC") String priceSort,
			@RequestParam(name = "pageNo",defaultValue = "0") int pageNo,
			@RequestParam(name = "pageSize",defaultValue = "5") int pageSize
			){
		return bookService.searchedBooks(searched,priceSort,pageNo,pageSize);
	}
}
