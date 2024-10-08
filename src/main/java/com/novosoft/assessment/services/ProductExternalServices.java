package com.novosoft.assessment.services;


import com.novosoft.assessment.entitys.Products;
import com.novosoft.assessment.repositorys.ProductsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@EnableCaching
public class ProductExternalServices {

    private static final String EXTERNAL_API_URL = "http://localhost:8081/products/prices";

    @Autowired
    private ProductsRepo productRepository;

    private final RestTemplate restTemplate;

    @Autowired
    public ProductExternalServices(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Cacheable("productPrices")
    public List<Products> fetchProductPrices() {
        return restTemplate.getForObject(EXTERNAL_API_URL, List.class);
    }

    @Scheduled(fixedRate = 86400000) // 24  60000
    public void updateProductPrices() {
        List<Products> latestPrices = fetchProductPrices();
        latestPrices.forEach(product -> {
            productRepository.save(product);
        });
    }


}
