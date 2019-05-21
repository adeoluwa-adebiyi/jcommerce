package com.vamos.jcommercekt

import com.vamos.jcommercekt.models.Product
import com.vamos.jcommercekt.models.Role
import com.vamos.jcommercekt.models.repositories.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.servlet.view.RedirectView
import java.sql.Blob
import java.util.*
import javax.sql.rowset.serial.SerialBlob

@Controller
@RequestMapping("/admin")
class AdminController(@Autowired var categoryStore: CategoryRepository,
                      @Autowired var userStore: UserRepository,
                      @Autowired var productStore: ProductRepository,
                      @Autowired var roleStore: RoleRepository,
                      @Autowired var privilegeStore: PrivilegeRepository){

    @GetMapping("/")
    fun home(model: Model):String{
        return "admin/index"
    }

    @GetMapping("/products")
    fun product(model: Model):String{
        val productList = productStore.findAll().map{serializeProduct(it)}
        model.addAttribute("products",productList)
        model.addAttribute("categories",this.categoryStore.findAll().map{it->it})
        return "admin/products"
    }

    @PostMapping("/products/create")
    fun createProduct(@RequestParam("name") productName:String,
                      @RequestParam("price") productPrice:String,
                      @RequestParam("image") productImage:MultipartFile,
                      @RequestParam("category") productCategory:String,
                      @RequestParam("description") productDescription:String,
                      @RequestParam("details") productDetails:String,
                      redirectView: RedirectView): RedirectView{
        println(productImage.originalFilename)
        val imageBase64 = ImageBase64Util.obtainBase64Uri(productImage.bytes,productImage)
        val product = Product()
        product.name = productName
        product.category = categoryStore.findByName(productCategory)
        product.productImage = SerialBlob(imageBase64.toByteArray())
        product.productPrice = productPrice.toDouble()
        product.productDescription = productDescription
        product.productDetail = productDetails
        this.productStore.save(product)
        print(productStore.findAll())
        redirectView.url ="/admin/products"
        return redirectView
    }

    @GetMapping("/products/manage")
    fun manageProduct(model: Model,
                      @RequestParam("id") productId:String):String{
        val product = productStore.findById(productId.toLong())
        model.addAttribute("product",product)
        return "admin/manage_product"
    }

    @GetMapping("/products/delete")
    fun deleteProduct(redirectView: RedirectView,
                      @RequestParam("id") productId:String):RedirectView{
        val product = productStore.findById(productId.toLong())
        productStore.delete(product)
        redirectView.url = "/admin/products"
        return redirectView
    }

    fun serializeProduct(product:Product):Properties{
        var properties:Properties = Properties()
        properties.setProperty("id",product.id.toString())
        properties.setProperty("name",product.name)
        properties.setProperty("description",product.productDescription)
        properties.setProperty("price",product.productPrice.toString())
        properties.setProperty("image",String(product.productImage.binaryStream.readAllBytes()))
        return properties
    }


}