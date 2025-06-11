package com.example.demo.core.comment.domain

class Comment {
    var id: String? = null
        private set
    var text: String private set
    var userId: Long private set

    constructor(text: String, userId: Long) {
        this.text = text
        this.userId = userId
    }

    fun changeText(text: String) {
        this.text = text
    }
}
