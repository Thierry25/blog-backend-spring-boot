package com.thierry.marcelin.bloggingapp.services.impl;

import com.thierry.marcelin.bloggingapp.dto.PostDTO;
import com.thierry.marcelin.bloggingapp.models.Post;
import com.thierry.marcelin.bloggingapp.repositories.PostRepository;
import com.thierry.marcelin.bloggingapp.services.PostService;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    @Override
    public PostDTO createNewPost(PostDTO postDTO) {
        // convert DTO to entity to be saved in repo
        var post = new Post();
        post.setTitle(postDTO.getTitle());
        post.setDescription(postDTO.getDescription());
        post.setContent(postDTO.getContent());
        postRepository.save(post);

        // convert entity to DTO
        var postResponse = new PostDTO();
        postResponse.setId(post.getId());
        postResponse.setTitle(post.getTitle());
        postResponse.setDescription(post.getDescription());
        postResponse.setContent(post.getContent());
        return postResponse;
    }
}
