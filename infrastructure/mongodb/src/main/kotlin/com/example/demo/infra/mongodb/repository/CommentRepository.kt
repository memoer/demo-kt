package com.example.demo.infra.mongodb.repository

import com.example.demo.infra.mongodb.entity.CommentEntity
import org.springframework.data.mongodb.repository.MongoRepository

interface CommentRepository : MongoRepository<CommentEntity, String>
