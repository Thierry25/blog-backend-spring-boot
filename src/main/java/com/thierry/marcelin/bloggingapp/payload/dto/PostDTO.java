package com.thierry.marcelin.bloggingapp.payload.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {
    private Long id;
    @Size(min=5, max=120, message = "Your post's title should be between 5 and 120 characters")
    private String title;
    @Size(min=10, max=120, message = "Your post's description should be between 10 and 120 characters")
    private String description;
    @Size(min = 10, max = 1440, message = "Your post content should be between 10 and 1440 characters")
    private String content;
}
