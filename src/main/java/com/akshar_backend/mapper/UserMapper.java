package com.akshar_backend.mapper;

import com.akshar_backend.dtos.BookDto;
import com.akshar_backend.dtos.UserDto;
import com.akshar_backend.entity.Book;
import com.akshar_backend.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserMapper {

    public UserDto toDto(User user){
        UserDto userDto = new UserDto();

        userDto.setId(user.getId());
        userDto.setName(user.getName());

        List<BookDto> bookDtos = new ArrayList<>();
        List<BookDto> addedToCart = new ArrayList<>();
        List<BookDto> purchasedBooks = new ArrayList<>();
        for (Book book:user.getUserBooks()) {
            bookDtos.add(new BookMapper().toDto(book));
        }
        for (Book book:user.getAddToCart()) {
            addedToCart.add(new BookMapper().toDto(book));
        }
        for (Book book:user.getPurchasedBooks()) {
            purchasedBooks.add(new BookMapper().toDto(book));
        }
        userDto.setUserBooks(bookDtos);
        userDto.setAddToCart(addedToCart);
        userDto.setPurchasedBooks(purchasedBooks );
        return userDto;
    }
}
