package com.example.demo.core.comment.domain

import com.example.demo.core.common.domain.Base
import java.time.LocalDateTime

class Comment : Base<String> {
    var text: String private set
    var userId: Long private set

    constructor(text: String, userId: Long) : super() {
        this.text = text
        this.userId = userId
    }

    fun changeText(text: String) {
        this.text = text
        this.updatedAt = LocalDateTime.now()
    }
}
