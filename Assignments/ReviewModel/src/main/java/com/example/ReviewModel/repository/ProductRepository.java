package com.example.ReviewModel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ReviewModel.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
