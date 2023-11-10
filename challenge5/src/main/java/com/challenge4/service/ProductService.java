package com.challenge4.service;

import com.challenge4.model.merchants;
import com.challenge4.model.products;
import com.challenge4.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    public List<products> getAll() {
        return productRepository.findAll();
    }

    public products addProduct(products products){
       return productRepository.save(products);
    }
    public products getById(UUID idProduct) {
        Optional<products> productsOptional = productRepository.findById(idProduct);
        if(productsOptional.isEmpty()){
            return null;
        }
        return productsOptional.get();

    }

    public products delete(UUID IdProduct) {
        Optional<products> productsOptional = productRepository.findById(IdProduct);
        if(productsOptional.isEmpty()){
            return null;
        }
        products deleteProduct = productsOptional.get();
        productRepository.deleteById(IdProduct);
        return deleteProduct;
    }

    public products updateProduct(UUID productId, products updatedProduct) {
        products existingProduct = null;
        try {
            existingProduct = productRepository.findById(productId)
                    .orElseThrow(() -> new ChangeSetPersister.NotFoundException());
        } catch (ChangeSetPersister.NotFoundException e) {
            throw new RuntimeException(e);
        }


        existingProduct.setName(updatedProduct.getName());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setMerchant(updatedProduct.getMerchant());

        return productRepository.save(existingProduct);
    }
}
