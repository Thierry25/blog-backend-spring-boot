package com.thierry.marcelin.bloggingapp.controllers;

import com.thierry.marcelin.bloggingapp.dto.PostDTO;
import com.thierry.marcelin.bloggingapp.services.PostService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<PostDTO>> getAllPosts(){
        return new ResponseEntity<>(postService.getAllPosts(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable long id){
        return new ResponseEntity<>(postService.getPostById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDTO> updatePost(@Valid @RequestBody PostDTO postDTO, @PathVariable long id){
        return new ResponseEntity<>(postService.update(postDTO, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable long id){
        return new ResponseEntity<>(postService.deletePost(id), HttpStatus.OK);
    }
}
