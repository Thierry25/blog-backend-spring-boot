package com.thierry.marcelin.bloggingapp.services;

import com.thierry.marcelin.bloggingapp.dto.PostDTO;

import java.util.List;

public interface PostService {
    PostDTO createNewPost(PostDTO postDTO);
    List<PostDTO> getAllPosts();
    PostDTO getPostById(long id);
    PostDTO update(PostDTO postDTO, long id);
    void deletePost(long id);
}
