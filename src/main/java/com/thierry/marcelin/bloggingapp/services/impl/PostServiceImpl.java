package com.thierry.marcelin.bloggingapp.services.impl;

import com.thierry.marcelin.bloggingapp.dto.PostDTO;
import com.thierry.marcelin.bloggingapp.exceptions.ResourceNotFoundException;
import com.thierry.marcelin.bloggingapp.models.Post;
import com.thierry.marcelin.bloggingapp.repositories.PostRepository;
import com.thierry.marcelin.bloggingapp.services.PostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    @Override
    public PostDTO createNewPost(PostDTO postDTO) {
        // convert DTO to entity to be saved in repo
        var post = mapToEntity(postDTO);
        postRepository.save(post);

        // convert entity to DTO
        return mapToDto(post);
    }

    @Override
    public List<PostDTO> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public PostDTO getPostById(long id) {
        var post = postRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Resource Not Found"));
        return mapToDto(post);
    }

    @Override
    public PostDTO update(PostDTO postDTO, long id) {
        var post = postRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Resource Not Found"));
        post.setTitle(postDTO.getTitle());
        post.setDescription(postDTO.getDescription());
        post.setContent(postDTO.getContent());

        var savedPost = postRepository.save(post);
        return mapToDto(savedPost);
    }

    @Override
    public void deletePost(long id) {
        var post = postRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Resource Not Found"));
        postRepository.delete(post);

    }

    // Convert entity to DTO
    private PostDTO mapToDto(Post post){
        return new PostDTO(post.getId(), post.getTitle(), post.getDescription(), post.getContent());
    }

    // Convert DTO to entity
    private Post mapToEntity(PostDTO postDTO){
        return new Post(postDTO.getTitle(), postDTO.getDescription(), postDTO.getContent());
    }

}
