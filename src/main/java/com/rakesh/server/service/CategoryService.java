package com.rakesh.server.service;

import com.rakesh.server.model.Category;
import com.rakesh.server.repo.Categoryrep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    Categoryrep categoryrep;
    public List<Category> getAllCate()
    {
        return categoryrep.findAll();
    }
    public void addCategory(Category category)
    {
        categoryrep.save(category);
    }
    public void removeCateByid(int id)
    {
        categoryrep.deleteById(id);
    }
    public Optional<Category> updateCateByid(int id)
    {
        return categoryrep.findById(id);
    }
    public Optional<Category> getCategoryByid(int id)
    {
      return categoryrep.findById(id);
    }
}
