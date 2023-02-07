package com.thierry.marcelin.bloggingapp.services;

import com.thierry.marcelin.bloggingapp.dto.PostDTO;

public interface PostService {
    PostDTO createNewPost(PostDTO postDTO);
}
