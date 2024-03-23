package com.project.controllers;

import com.project.payloads.CommentDto;
import com.project.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentDto> addComment(@RequestBody CommentDto commentDto, @RequestParam Integer postId) {
        return ResponseEntity.ok(commentService.addComment(commentDto, postId));
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@RequestBody CommentDto commentDto, @PathVariable Integer commentId) {
        return ResponseEntity.ok(commentService.updateComment(commentDto, commentId));
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Boolean> deleteComment(@PathVariable Integer commentId) {
        return ResponseEntity.ok(commentService.deleteComment(commentId));
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<List<CommentDto>> getAllCommentsByPostId(@PathVariable Integer postId) {
        return ResponseEntity.ok(commentService.getAllCommentsByPostId(postId));
    }

}
