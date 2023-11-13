package com.rakesh.server.service;

import com.rakesh.server.repo.ProductRepo;
import com.rakesh.server.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
@Autowired
ProductRepo productRepo;
   public List<Product> getAllProducts()
   {
    return productRepo.findAll();
   }
    public void addProduct(Product product)
    {
        productRepo.save(product);
    }
    public void removeProduct(Long id)
    {
        productRepo.deleteById(id);
    }
    public Optional<Product> updateProductByid(Long id)
    {
        return productRepo.findById(id);
    }
    public List<Product> getAllProductsByCategoryId(int id)
    {
        return productRepo.findAllByCategory_Id(id);
    }
}
