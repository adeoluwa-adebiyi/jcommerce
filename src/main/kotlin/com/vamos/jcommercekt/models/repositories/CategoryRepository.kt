package com.vamos.jcommercekt.models.repositories

import com.vamos.jcommercekt.models.Category
import com.vamos.jcommercekt.models.Product

interface CategoryRepository: BaseRepository<Category,Long>{
    fun findAll():List<Category>
    fun findByName(name:String):Category
}