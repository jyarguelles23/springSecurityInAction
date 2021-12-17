package com.viqsystems.chaptersix.Repositories;

import com.viqsystems.chaptersix.Entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {
}
