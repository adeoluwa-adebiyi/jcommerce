package com.vamos.jcommercekt.loaders

import com.vamos.jcommercekt.models.Category
import com.vamos.jcommercekt.models.repositories.CategoryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component

@Component
class InitLoader (@Autowired var categoryStore: CategoryRepository) : ApplicationListener<ApplicationReadyEvent> {
   override fun onApplicationEvent(event: ApplicationReadyEvent) {
//        val list = listOf<String>("Fashion","Sport","Electronics","Computers & Accessories", "Phones & Tablets")
//        list.forEach{ loadCategory(it,categoryStore)}
//        println(categoryStore.findAll())
    }
}

fun loadCategory(categoryName:String,categoryStore: CategoryRepository) {
    val category = Category()
    category.name = categoryName
    categoryStore.save(category)
}