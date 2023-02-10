package com.thierry.marcelin.bloggingapp.controllers;

import com.thierry.marcelin.bloggingapp.payload.dto.CommentDTO;
import com.thierry.marcelin.bloggingapp.services.CommentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/posts/{id}/comments")
    public ResponseEntity<CommentDTO> createNewComment(@PathVariable long id, @Valid @RequestBody CommentDTO commentDTO){
        return new ResponseEntity<>(commentService.createNewComment(id, commentDTO), HttpStatus.CREATED);
    }
}
