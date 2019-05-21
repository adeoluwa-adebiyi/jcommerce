package com.vamos.jcommercekt.models.repositories

import org.springframework.data.repository.Repository

interface BaseRepository<T,ID> : Repository<T,ID>{
    fun save(_object: T):Unit
    fun delete(_object: T):Unit
}