package com.example.demo.client.alarm

import org.springframework.stereotype.Service

@Service
class AlarmClient(private val stub: AlarmServiceGrpc.AlarmServiceBlockingStub)
