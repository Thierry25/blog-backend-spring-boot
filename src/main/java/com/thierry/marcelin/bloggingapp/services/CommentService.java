package com.thierry.marcelin.bloggingapp.services;

import com.thierry.marcelin.bloggingapp.payload.dto.CommentDTO;

public interface CommentService {
    CommentDTO createNewComment(long id, CommentDTO comment);
}
