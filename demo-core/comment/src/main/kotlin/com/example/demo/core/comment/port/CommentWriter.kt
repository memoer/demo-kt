package com.example.demo.core.comment.port

import com.example.demo.core.comment.domain.Comment

interface CommentWriter {
    fun write(comment: Comment)
    fun delete(comment: Comment)
}
