package com.akshar_backend.controller;

import com.akshar_backend.dtos.BookDto;
import com.akshar_backend.repository.BookRepository;
import com.akshar_backend.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping("/")
    public ResponseEntity<List<BookDto>> getAllBooks(){
        return ResponseEntity.ok().body(bookService.getAllBooks());
    }

    @GetMapping("/{category}")
    public ResponseEntity<List<BookDto>> getBooksByCategory(@PathVariable String category){
        return ResponseEntity.ok().body(bookService.getByCategory(category));
    }

//    @PutMapping("/update-book/{id}")
//    public ResponseEntity<String> updateBook(@PathVariable long id){
//        return ResponseEntity.ok().body(bookService.updateBook(id));
//    }

}
