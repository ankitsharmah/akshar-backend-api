package com.akshar_backend.service;

import com.akshar_backend.dtos.BookDto;
import com.akshar_backend.entity.Book;
import com.akshar_backend.mapper.BookMapper;
import com.akshar_backend.repository.BookRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public List<BookDto> getAllBooks(){
        List<Book> books = bookRepository.findAll();

        List<BookDto> bookDtoList = new ArrayList<>();

        for (Book book:books) {
            bookDtoList.add(new BookMapper().toDto(book));
        }

        return bookDtoList;
    }
//    public String addBook(BookDto bookDto){
//
//        Book book = new BookMapper().toEntity(bookDto);
//        bookRepository.save(book);
//
//        return "book is added";
//    }

    public String updateBook(BookDto bookDto){
        Book book = bookRepository.findById(bookDto.getId()).get();

        book.setBookCategory(bookDto.getBookCategory());
        book.setBookTitle(bookDto.getBookTitle());
        book.setRating(bookDto.getRating());
        book.setAuthorName(bookDto.getAuthorName());
        book.setImageUrl(bookDto.getImageUrl());
        book.setBookDescription(bookDto.getBookDescription());

        bookRepository.save(book);

        return "book updated successfully";
    }

    public List<BookDto> getByCategory(String category) {

        List<Book> books = bookRepository.findByBookCategory(category);
        List<BookDto> bookDtos = new ArrayList<>();

        for (Book book:books
             ) {
            bookDtos.add(new BookMapper().toDto(book));
        }

        return bookDtos;
    }

    public List<BookDto> getByRating(float rating){
        List<Book> books = bookRepository.findByRating(rating);

        List<BookDto> bookDtos = new ArrayList<>();

        for (Book book:books){
            bookDtos.add(new BookMapper().toDto(book));
        }

        return bookDtos;

    }
}
