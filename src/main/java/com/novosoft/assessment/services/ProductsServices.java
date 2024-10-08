package com.novosoft.assessment.services;


import com.novosoft.assessment.entitys.Products;
import com.novosoft.assessment.repositorys.ProductsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductsServices {

    @Autowired
    private ProductsRepo productsRepo;

    public Products createProducts(Products products){
        return productsRepo.save(products);
    }

    public Page<Products> getAllProducts(Pageable pageable){
        return productsRepo.findAll(pageable);
    }

    public Optional<Products> getProductById(int id){
        return productsRepo.findById(id);
    }

    public Products updateProduct(int id,Products productsDetails){
        Optional<Products> productsOptional = productsRepo.findById(id);

        if (productsOptional.isPresent()){
            Products existingProduct = productsOptional.get();
            existingProduct.setName(productsDetails.getName());
            existingProduct.setDescription(productsDetails.getDescription());
            existingProduct.setPrice(productsDetails.getPrice());
            return productsRepo.save(existingProduct);
        }else {
            return null;
        }

        }
    public boolean deleteProducts(int id){
        Optional<Products> optionalProducts = productsRepo.findById(id);

        if (optionalProducts.isPresent()){
            productsRepo.deleteById(id);
            return true;
        }else {
            return false;
        }
    }
}

