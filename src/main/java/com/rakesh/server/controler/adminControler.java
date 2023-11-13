package com.rakesh.server.controler;

import com.rakesh.server.model.Category;
import com.rakesh.server.service.ProductService;
import com.rakesh.server.dtObject.ProductDto;
import com.rakesh.server.model.Product;
import com.rakesh.server.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Controller
public class adminControler {
    public static  String uplodeDir=System.getProperty("user.dir")+"/src/main/resources/static/productImages";
    //Creating Category Section.......
    @Autowired
    CategoryService categoryService;
    @GetMapping("/admin")
    public  String adminHome()
    {
        return  "adminHome";
    }
    @GetMapping("/admin/categories")
    public  String admincatagory(Model model)
    {

        model.addAttribute("categories",categoryService.getAllCate());
        return  "categories";
    }
    @GetMapping("/admin/categories/add")
    public  String catAdd(Model model)
    {
        model.addAttribute("category",new Category());
        return  "categoriesAdd";
    }

    @PostMapping("/admin/categories/add")
    public  String postAddCat(@ModelAttribute("category") Category category)
    {
       categoryService.addCategory(category);
        return  "redirect:/admin/categories";
    }

    @GetMapping("/admin/categories/delete/{id}")
    public String deleteCate(@PathVariable int id)
    {
     categoryService.removeCateByid(id);
     return "redirect:/admin/categories";
    }

    @GetMapping("/admin/categories/update/{id}")
    public String updateCate(@PathVariable int id,Model model)
    {
        Optional<Category> category =categoryService.updateCateByid(id);
        if(category.isPresent())
        {
            model.addAttribute("category",category.get());
            return "categoriesAdd";
        }
        else
        {
            return "404";
        }

    }

//Creating product section......
    @Autowired
ProductService productService;
    @GetMapping("/admin/products")
    public String adminProduct(Model model)
    {
        model.addAttribute("products",productService.getAllProducts());
        return "products";
    }

    @GetMapping("/admin/products/add")
    public  String productAdd(Model model)
    {
        model.addAttribute("productDto",new ProductDto());
        model.addAttribute("categories",categoryService.getAllCate());
        return  "productsAdd";
    }

    @PostMapping("/admin/products/add")
    public  String addProduct(@ModelAttribute("productDto")ProductDto productDto,
                              @RequestParam("productImage")MultipartFile file,
                              @RequestParam("imgName")String imgName) throws IOException
    {

        Product product=new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setCategory(categoryService.getCategoryByid(productDto.getCategory_id()).get());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        String imageUUID;
        if (!file.isEmpty())
        {
            imageUUID=file.getOriginalFilename();
            Path fileNameAndPath= Paths.get(uplodeDir,imageUUID);
            Files.write(fileNameAndPath,file.getBytes());
        }
        else
        {
            imageUUID=imgName;
        }
        product.setImageName(imageUUID);
        productService.addProduct(product);

        return  "redirect:/admin/products";
    }
    @GetMapping("/admin/product/delete/{id}")
    public String deleteProduct(@PathVariable Long id)
    {
        productService.removeProduct(id);
        return "redirect:/admin/products";
    }

    @GetMapping("/admin/product/update/{id}")
    public String updateProduct(@PathVariable Long id,Model model)
    {
        Product product=productService.updateProductByid(id).get();
        ProductDto productDto=new ProductDto();

        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setCategory_id(product.getCategory().getId());
        productDto.setPrice(product.getPrice());
        product.setDescription(product.getDescription());
        product.setImageName(product.getImageName());

        model.addAttribute("categories",categoryService.getAllCate());
        model.addAttribute("productDto",productDto);
        return "productsAdd";

    }



}
