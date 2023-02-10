package com.thierry.marcelin.bloggingapp.services;

import com.thierry.marcelin.bloggingapp.payload.PostResponse;
import com.thierry.marcelin.bloggingapp.payload.dto.PostDTO;

import java.util.List;

public interface PostService {
    PostDTO createNewPost(PostDTO postDTO);
    PostResponse getAllPosts(int pageNo, int pageSize);
    PostDTO getPostById(long id);
    PostDTO update(PostDTO postDTO, long id);
    void deletePost(long id);
}
