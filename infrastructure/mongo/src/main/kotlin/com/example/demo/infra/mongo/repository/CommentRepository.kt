package com.example.demo.infra.mongo.repository

import com.example.demo.infra.mongo.entity.CommentEntity
import org.springframework.data.mongodb.repository.MongoRepository

interface CommentRepository : MongoRepository<CommentEntity, String>
