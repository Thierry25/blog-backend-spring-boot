package com.thierry.marcelin.bloggingapp.controllers;

import com.thierry.marcelin.bloggingapp.dto.PostDTO;
import com.thierry.marcelin.bloggingapp.services.PostService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService){
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<PostDTO> addNewPost(@Valid @RequestBody PostDTO postDTO){
        return new ResponseEntity<>(postService.createNewPost(postDTO), HttpStatus.CREATED);
    }
}
