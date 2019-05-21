package com.vamos.jcommercekt.models.repositories

import com.vamos.jcommercekt.models.Category
import com.vamos.jcommercekt.models.Product

interface ProductRepository: BaseRepository<Product,Long>{
    fun findAll():List<Product>
    fun findByName(name:String):Product
    fun findById(id:Long):Product
    fun findByCategory(category: Category):List<Product>
}