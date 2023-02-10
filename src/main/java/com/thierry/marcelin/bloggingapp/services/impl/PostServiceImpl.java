package com.thierry.marcelin.bloggingapp.services.impl;

import com.thierry.marcelin.bloggingapp.payload.PostResponse;
import com.thierry.marcelin.bloggingapp.payload.dto.PostDTO;
import com.thierry.marcelin.bloggingapp.exceptions.ResourceNotFoundException;
import com.thierry.marcelin.bloggingapp.models.Post;
import com.thierry.marcelin.bloggingapp.repositories.PostRepository;
import com.thierry.marcelin.bloggingapp.services.PostService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
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
    public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {

        // Ascending or descending
        var sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
//        Only creates a pageable object
//        var pageable = PageRequest.of(pageNo, pageSize);
        var pageable = PageRequest.of(pageNo, pageSize, sort);

        var posts = postRepository.findAll(pageable);
        var content = postRepository.findAll(pageable).getContent()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
        return new PostResponse(
                content, posts.getNumber(), posts.getSize(),
                posts.getTotalElements(), posts.getTotalPages(), posts.isLast());
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
    private PostDTO mapToDto(Post post) {
        return new PostDTO(post.getId(), post.getTitle(), post.getDescription(), post.getContent());
    }

    // Convert DTO to entity
    private Post mapToEntity(PostDTO postDTO) {
        return new Post(postDTO.getTitle(), postDTO.getDescription(), postDTO.getContent());
    }

}
