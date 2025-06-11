package com.example.demo.core.comment.port

import com.example.demo.core.comment.domain.Comment

interface CommentReader {
    fun readById(id: String): Comment
}
