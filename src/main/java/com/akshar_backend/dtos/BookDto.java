package com.akshar_backend.dtos;

import com.akshar_backend.entity.Status;
import lombok.Data;

@Data
public class BookDto {
    private long id;
    private String bookTitle;
    private String authorName;
    private int bookPrice;
    private String imageUrl;
    private String bookCategory;
    private String bookDescription;
    private Status status;
    private float rating;

}
