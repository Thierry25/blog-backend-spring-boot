package com.thierry.marcelin.bloggingapp.payload.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
    private Long id;
    @Size(min=2, max=24, message = "Please enter a name between 2 and 24 characters")
    private String name;
    @Email
    private String email;
    @Size(min=5, max = 1440, message = "Please enter a comment between 5 and 1440 characters")
    private String body;
}
