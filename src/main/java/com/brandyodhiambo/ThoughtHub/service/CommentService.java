package com.brandyodhiambo.ThoughtHub.service;

import com.brandyodhiambo.ThoughtHub.model.Comment;
import com.brandyodhiambo.ThoughtHub.payload.response.ApiResponse;
import com.brandyodhiambo.ThoughtHub.payload.request.CommentRequest;
import com.brandyodhiambo.ThoughtHub.payload.response.PagedResponse;
import com.brandyodhiambo.ThoughtHub.service.impl.UserPrincipal;

public interface CommentService {

    PagedResponse<Comment> getAllComments(Long postId, int page, int size);

    Comment addComment(CommentRequest commentRequest, Long postId, UserPrincipal currentUser);

    Comment getComment(Long postId, Long id);

    Comment updateComment(Long postId, Long id, CommentRequest commentRequest, UserPrincipal currentUser);

    ApiResponse deleteComment(Long postId, Long id, UserPrincipal currentUser);

}
