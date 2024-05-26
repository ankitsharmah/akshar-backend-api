package com.akshar_backend.mapper;


import com.akshar_backend.dtos.BookDto;
import com.akshar_backend.entity.Book;

public class BookMapper {

    public Book toEntity(BookDto bookDto){
        Book book = new Book();

        book.setBookCategory(bookDto.getBookCategory());
        book.setBookTitle(bookDto.getBookTitle());
        book.setRating(bookDto.getRating());
        book.setAuthorName(bookDto.getAuthorName());
        book.setImageUrl(bookDto.getImageUrl());
        book.setBookPrice(bookDto.getBookPrice());
        book.setBookDescription(bookDto.getBookDescription());
        book.setBookFor(bookDto.getStatus());
        return book;
    }

    public BookDto toDto(Book book){
        BookDto bookDto = new BookDto();

        bookDto.setBookCategory(book.getBookCategory());
        bookDto.setBookTitle(book.getBookTitle());
        bookDto.setRating(book.getRating());
        bookDto.setAuthorName(book.getAuthorName());
        bookDto.setImageUrl(book.getImageUrl());
        bookDto.setBookDescription(book.getBookDescription());
        bookDto.setId(book.getId());
        bookDto.setBookPrice(book.getBookPrice());
        bookDto.setStatus(book.getBookFor());
        return bookDto;
    }
}
