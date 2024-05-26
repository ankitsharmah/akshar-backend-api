package com.akshar_backend.controller;

import com.akshar_backend.dtos.BookDto;
import com.akshar_backend.dtos.UserDto;
import com.akshar_backend.entity.Book;
import com.akshar_backend.service.BookService;
import com.akshar_backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final BookService bookService;
    @PostMapping("/upload-book/{userId}")
    public ResponseEntity<String> addUserBooks(@PathVariable long userId, @RequestBody Book book){
        return ResponseEntity.ok().body(userService.addUserBook(userId,book));
    }
    @PutMapping("/add-to-cart/{userId}/{bookId}")
  public ResponseEntity<String> addToCart(@PathVariable long userId, @PathVariable long bookId){
        return ResponseEntity.ok().body(userService.addBookedMarked(userId,bookId));
    }
    @PutMapping("/purchased/{userId}/{bookId}")
    public ResponseEntity<String> purchasedBooks(@PathVariable long userId, @PathVariable long bookId){
        return ResponseEntity.ok().body(userService.addPurchasedBook(userId,bookId));
    }
   @GetMapping("/{id}")
    public ResponseEntity<UserDto> purchasedBooks(@PathVariable long id){
        return ResponseEntity.ok().body(userService.getUserById(id));
    }
    @GetMapping("")
    public ResponseEntity<List<BookDto>> books(){
        return ResponseEntity.ok().body(bookService.getAllBooks());
    }


}
