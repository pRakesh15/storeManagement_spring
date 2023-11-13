package com.rakesh.server.controler;

import com.rakesh.server.global.GlobalData;
import com.rakesh.server.service.ProductService;
import com.rakesh.server.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RestController;



@Controller
public class HomeControler {

    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;

    @GetMapping({"/", "/home"})
    public String home(Model model)
    {
        model.addAttribute("cartCount", GlobalData.cart.size());
        return "index";
    }
    @GetMapping("/shop")
    public String items(Model model)
    {
        model.addAttribute("cartCount",GlobalData.cart.size());
        model.addAttribute("categories",categoryService.getAllCate());
        model.addAttribute("products",productService.getAllProducts());

        return "shop";
    }
    @GetMapping("/shop/category/{id}")
    public String shopeBycategory(Model model, @PathVariable int id)
    {
        model.addAttribute("cartCount",GlobalData.cart.size());
        model.addAttribute("categories",categoryService.getAllCate());
        model.addAttribute("products",productService.getAllProductsByCategoryId(id));
        return "shop";
    }
    @GetMapping("/shop/viewproduct/{id}")
    public String viewProduct(Model model,@PathVariable Long id)
    {
        model.addAttribute("cartCount",GlobalData.cart.size());
        model.addAttribute("product",productService.updateProductByid(id).get());

        return "viewProduct";
    }


}
