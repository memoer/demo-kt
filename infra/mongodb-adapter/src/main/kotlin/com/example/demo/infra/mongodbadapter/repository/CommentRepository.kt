package com.example.demo.infra.mongodbadapter.repository

import com.example.demo.infra.mongodbadapter.entity.CommentEntity
import org.springframework.data.mongodb.repository.MongoRepository

interface CommentRepository : MongoRepository<CommentEntity, String>
