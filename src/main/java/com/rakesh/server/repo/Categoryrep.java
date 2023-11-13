package com.rakesh.server.repo;

import com.rakesh.server.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Categoryrep extends JpaRepository<Category,Integer> {
}
