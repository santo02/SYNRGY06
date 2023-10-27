package com.challenge4.service;

import com.challenge4.model.merchants;
import com.challenge4.model.products;
import com.challenge4.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    public List<products> getAll() {
        return productRepository.findAll();
    }

    public products addProduct(String name, Double price, merchants merchant){
        products product = new products();
        product.setName(name);
        product.setPrice(price);
        product.setMerchant(merchant);

       return productRepository.save(product);
    }
}
