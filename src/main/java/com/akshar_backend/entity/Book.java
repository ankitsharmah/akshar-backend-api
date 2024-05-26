package com.akshar_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "book_tb")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String bookTitle;
    private String authorName;
    private int bookPrice;
    private String imageUrl;
    private String bookCategory;
    @Column(length = 5000)
    private String bookDescription;
    @Enumerated(EnumType.STRING)
    private Status bookFor;
    private float rating;

}
