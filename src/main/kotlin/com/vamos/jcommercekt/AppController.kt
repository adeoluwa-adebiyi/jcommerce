package com.vamos.jcommercekt

import com.vamos.jcommercekt.models.Product
import com.vamos.jcommercekt.models.repositories.CategoryRepository
import com.vamos.jcommercekt.models.repositories.OrderItemDetailRepository
import com.vamos.jcommercekt.models.repositories.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.view.RedirectView
import java.util.*
import javax.servlet.http.HttpServletRequest
import kotlin.collections.ArrayList

data class CartItem(var id:Long)

@Controller
@RequestMapping("/")
class AppController(@Autowired var categoryStore: CategoryRepository,
                    @Autowired var productStore: ProductRepository){
    @GetMapping("/")
    fun index(model: Model,
              httpServletRequest: HttpServletRequest):String{
        val categories =  categoryStore.findAll()
        val list = ArrayList<String>()
        categories.forEach{list.add(it.name)}
        model.addAttribute("categories",list)
        println(httpServletRequest.session.getAttribute("test"))
        val cart = httpServletRequest.session.getAttribute("cart") as ArrayList<CartItem>?
        if(cart==null)
            model.addAttribute("cart_no","0")
        else
            model.addAttribute("cart_no",cart.size)
        return "user/index"
    }

    @GetMapping("/category/{name}")
    fun category(model: Model,
                  @PathVariable("name") categoryName:String,
                 httpServletRequest: HttpServletRequest):String{
        val _category = categoryStore.findByName(categoryName)
        model.addAttribute("category",_category)
        model.addAttribute("products",productStore.findByCategory(_category).map{serializeProduct(it)})
        val cart = httpServletRequest.session.getAttribute("cart") as ArrayList<CartItem>?
        if(cart==null)
            model.addAttribute("cart_no","0")
        else
            model.addAttribute("cart_no",cart.size)
        return "user/category"
    }

    @GetMapping("/product/{id}")
    fun product(model: Model,
                @PathVariable("id") id: Long,
                httpServletRequest: HttpServletRequest):String{
        model.addAttribute("product",serializeProduct(productStore.findById(id)))
        val cart = httpServletRequest.session.getAttribute("cart") as ArrayList<CartItem>?
        if(cart==null)
            model.addAttribute("cart_no","0")
        else
            model.addAttribute("cart_no",cart.size)
        return "user/product_info"
    }

    fun serializeProduct(product: Product): Properties {
        var properties: Properties = Properties()
        properties.setProperty("id",product.id.toString())
        properties.setProperty("name",product.name)
        properties.setProperty("description",product.productDescription?:"")
        properties.setProperty("price",product.productPrice.toString())
        properties.setProperty("image",String(product.productImage.binaryStream.readAllBytes()))
        properties.setProperty("details",product.productDetail?:"")
        return properties
    }

    @GetMapping("/product/addtocart/{productId}")
    fun addProductToCart(httpServletRequest: HttpServletRequest,
                         @PathVariable("productId") productId:Int,
                         redirectView: RedirectView): RedirectView {
        redirectView.url = "/"
        println("In Cart Request")
        httpServletRequest.session.setAttribute("test","Testing")
            if (httpServletRequest.session.getAttribute("cart")!=null) {
                val cart = httpServletRequest.session.getAttribute("cart") as ArrayList<CartItem>
                cart.add(CartItem(productId.toLong()))
                httpServletRequest.session.setAttribute("cart", cart)
                println("Cart created: ${cart.toString()}")
            }
            //create new cart
            else{
                val cart = ArrayList<CartItem>()
                cart.add(CartItem(productId.toLong()))
                httpServletRequest.session.setAttribute("cart",cart)
                println("Cart updated: ${cart.toString()}")
            }
//        httpServletRequest.session.
        return redirectView
    }

    @GetMapping("/checkout")
    fun serveCheckout(httpServletRequest: HttpServletRequest,model: Model):String{
        val cart = httpServletRequest.session.getAttribute("cart") as ArrayList<CartItem>?
        if(cart==null)
            model.addAttribute("cart_no","0")
        else
            model.addAttribute("cart_no",cart.size)
        model.addAttribute("products",cart?.map{it->serializeProduct(productStore.findById(it.id))})
        var totalPrice = 0.0
        cart?.forEach{it->totalPrice += productStore.findById(it.id).productPrice}
        val storePaymentMail = "deeman24@yahoo.com"
        var checkoutObject = Properties()
        checkoutObject["price"] = totalPrice
        checkoutObject["paystackPrice"] = totalPrice * 100
        checkoutObject["storePaymentMail"] = storePaymentMail
        checkoutObject["customerMail"] = "vanilla_apps@yahoo.com"
        checkoutObject["storeName"] = "JCommerce.com"
        println("Checkout: $checkoutObject")
        model.addAttribute("checkout",checkoutObject)
        return "user/checkout"
    }

    @GetMapping("/checkout/pay")
    fun processCheckout(httpServletRequest: HttpServletRequest,
                        redirectView: RedirectView):RedirectView{
        redirectView.url = "/"
        return redirectView
    }
}

@RestController
class PaymentEndpoint(@Autowired var orderStore: OrderItemDetailRepository){
    @PostMapping("/order/save")
    fun saveOrder(){

    }
}