package com.thierry.marcelin.bloggingapp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "posts", uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})})
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min=5, max=120, message = "Your post's title should be between 5 and 120 characters")
    private String title;
    @Size(min=10, max=120, message = "Your post's description should be between 5 and 120 characters")
    private String description;
    @Size(min = 10, max = 1440, message = "Your post content should be between 10 and 1440 characters")
    private String content;
    @FutureOrPresent
    private LocalDate time;
}
