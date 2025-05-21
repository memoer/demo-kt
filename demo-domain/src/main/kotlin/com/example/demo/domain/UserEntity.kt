package com.example.demo.domain

class UserEntity {
    var id: Long private set;
    var name: String private set;
    var password: String private set;

    constructor(name: String, password:String){
        this.id = -1L
        this.name = name
        this.password = password
    }

    fun changeName(name: String) {
        this.name = name
    }

    fun changePassword(password: String){
        this.password = password
    }
}

