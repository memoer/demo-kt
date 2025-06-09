package com.example.demo.infrastructure.mongodb.repository

import com.example.demo.infrastructure.mongodb.entity.CommentEntity
import org.springframework.data.mongodb.repository.MongoRepository

interface CommentRepository : MongoRepository<CommentEntity, String>
