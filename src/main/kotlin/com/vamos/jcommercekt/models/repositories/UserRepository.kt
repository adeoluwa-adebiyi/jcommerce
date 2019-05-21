package com.vamos.jcommercekt.models.repositories

import com.fasterxml.jackson.databind.ser.Serializers
import com.vamos.jcommercekt.models.User

interface UserRepository: BaseRepository<User,Long>{
    fun findById(id: Long):User
}