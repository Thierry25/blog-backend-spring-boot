package com.thierry.marcelin.bloggingapp.services;

import com.thierry.marcelin.bloggingapp.payload.dto.CommentDTO;

import java.util.List;

public interface CommentService {
    CommentDTO createNewComment(long postId, CommentDTO comment);
    List<CommentDTO> getAllComments(long postId);
    CommentDTO getCommentById(long postId, long commentId);
    CommentDTO updateComment(long postId, long commentId, CommentDTO newData);
}
