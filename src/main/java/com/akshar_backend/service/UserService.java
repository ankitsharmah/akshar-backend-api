package com.akshar_backend.service;

import com.akshar_backend.dtos.BookDto;
import com.akshar_backend.dtos.UserDto;
import com.akshar_backend.entity.Book;
import com.akshar_backend.entity.User;
import com.akshar_backend.mapper.BookMapper;
import com.akshar_backend.mapper.UserMapper;
import com.akshar_backend.repository.BookRepository;
import com.akshar_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
private final UserRepository userRepository;
private final BookRepository bookRepository;
private final PasswordEncoder passwordEncoder;
    public String addBookedMarked(long userId, long bookId) {
        User user = userRepository.findById(userId).get();
        Book book = bookRepository.findById(bookId).get();

        user.getAddToCart().add(book);
        userRepository.save(user);

        return "book added to cart successfully";
    }

    public String addUserBook(long userId , Book book){
        User user = userRepository.findById(userId).get();

        user.getUserBooks().add(book);
        userRepository.save(user);

        return "user add new book successfully";
    }

    public String addPurchasedBook(long userId,long bookId){
        User user = userRepository.findById(userId).get();
        Book book = bookRepository.findById(bookId).get();

        user.getPurchasedBooks().add(book);
        userRepository.save(user);

        return "user purchased book added successfully";
    }

    public UserDto getUserById(long id){
        User user = userRepository.findById(id).get();
        return new UserMapper().toDto(user);
    }

    public String updateUserName(long id,String name){
        User user = userRepository.findById(id).get();

        user.setName(name);
        userRepository.save(user);
        return "user updated successfully";
    }

    public String updatePassword(long id ,String password){
        User user = userRepository.findById(id).get();

        user.setPassword(passwordEncoder.encode(password));
        return "password updated successfully";

    }

    public String removeFromFavorites(long userId,long bookId){
        User user = userRepository.findById(userId).get();

        for (Book book:user.getAddToCart()
             ) {
            if (book.getId()==bookId){
                user.getAddToCart().remove(book);
            }
        }
        return "book removed successfully";
    }
    public String removedFromDonation(long userId,long bookId){
        User user = userRepository.findById(userId).get();

        for (Book book:user.getUserBooks()
        ) {
            if (book.getId()==bookId){
                user.getUserBooks().remove(book);
            }
        }
        return "book removed successfully";

    }

    public String addToTheWishList(long userId,long bookId){
        User user = userRepository.findById(userId).get();
        Book book = bookRepository.findById(bookId).get();

        user.getWishList().add(book);
        userRepository.save(user);

        return "book added to Wishlist successfully";
    }

//    public BookDto getBookByCat(String cat){
//        return new BookMapper().toDto(bookRepository.findByBookCategory(cat));
//    }


}
