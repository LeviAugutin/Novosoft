package com.novosoft.assessment.controller;


import com.novosoft.assessment.entitys.Products;
import com.novosoft.assessment.exception.InvalidProductException;
import com.novosoft.assessment.exception.NoProductsFoundException;
import com.novosoft.assessment.exception.productNotFoundException;
import com.novosoft.assessment.services.ProductsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping(value = "/novo")
public class ProductsController {

    @Autowired
    private ProductsServices productsServices;

    @GetMapping(value = "/get/products")
    public ResponseEntity<Page<Products>> getAllProducts(
            @RequestParam(defaultValue =   "0") int page,
            @RequestParam(defaultValue = "5") int size
    ){
        Pageable pageable = PageRequest.of(page, size);
        Page<Products> products = productsServices.getAllProducts(pageable);

        if(products.isEmpty())
        {
            throw new NoProductsFoundException("no products available");
        }
        return ResponseEntity.ok(products);
    }

    @GetMapping(value = "products/{id}")
    public ResponseEntity<?> getProductById(@RequestParam int id) {
        Products products = productsServices.getProductById(id)
                .orElseThrow(() -> new productNotFoundException("product not found for id " + id));
        return ResponseEntity.ok(products);

    }

    @PostMapping(value = "create/product")
    public ResponseEntity<Products> createProduct(@RequestBody Products product) {
        try{
            Products createProducts = productsServices.createProducts(product);

            return new ResponseEntity<>(createProducts,HttpStatus.CREATED);
        } catch (Exception e)
        {
            throw new InvalidProductException("Invalid data");
        }

    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Products> updateProduct(@RequestParam int id, @RequestBody Products productDetails) {
        Products existingProduct = productsServices.getProductById(id)
                .orElseThrow(() -> new productNotFoundException("Product not found for ID: " + id));
        Products updatedProduct = productsServices.updateProduct(id, productDetails);

        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable int id) {
        boolean deleted = productsServices.deleteProducts(id);
        if (!deleted) {
            throw new productNotFoundException("Product not found for ID: " + id);
        }
        return ResponseEntity.noContent().build();
    }
}
