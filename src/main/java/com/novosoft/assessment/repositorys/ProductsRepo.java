package com.novosoft.assessment.repositorys;


import com.novosoft.assessment.entitys.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepo extends JpaRepository<Products,Integer> {
}
