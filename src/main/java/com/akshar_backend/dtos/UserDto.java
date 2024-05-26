package com.akshar_backend.dtos;

import com.akshar_backend.entity.Role;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class UserDto {
    private long id;
    private String name;
    private Role role;
    private LocalDateTime OtpGeneratedTime;
    private List<BookDto> userBooks= new ArrayList<>();
    private List<BookDto> addToCart = new ArrayList<>();
    private List<BookDto> purchasedBooks = new ArrayList<>();
}
