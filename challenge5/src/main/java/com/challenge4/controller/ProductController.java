package com.challenge4.controller;

import com.challenge4.model.merchants;
import com.challenge4.model.products;
import com.challenge4.response.ResponseHandler;
import com.challenge4.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Component
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;
    private final static Logger logger =  LoggerFactory.getLogger(MerchantController.class);

    @PostMapping("add/product")
    public ResponseEntity<Object> add(@RequestBody products product){
        productService.addProduct(product) ;
        return ResponseHandler.generateResponse("Berhasil Menambahkan Product", HttpStatus.OK, product);

    }

    @GetMapping
    public ResponseEntity<List<products>> getALL(){
        List<products> productsList = productService.getAll();
        return ResponseEntity.ok(productsList);
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> show(@PathVariable("id") UUID idProduct){
        products product = productService.getById(idProduct);

        if (product != null){
            return ResponseHandler.generateResponse("Berhasil", HttpStatus.OK, product);
        }else {
            return ResponseHandler.generateResponse("Data Tidak Ditemukan", HttpStatus.NOT_FOUND, null);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") UUID idMerchant){
        try {
            products deleteProduct = productService.delete(idMerchant);

            if (deleteProduct != null) {
                return  ResponseHandler.generateResponse("Data berhasil di hapus!", HttpStatus.OK, deleteProduct );
            } else {
                return  ResponseHandler.generateResponse("Data Tidak Ditemukan", HttpStatus.NOT_FOUND, null);
            }
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }
    @PatchMapping("{productId}")
    public ResponseEntity<Object> updateProduct(@PathVariable UUID productId,
                                                @RequestBody products updatedProduct) {
        products updatedProductResponse = productService.updateProduct(productId, updatedProduct);
        return ResponseHandler.generateResponse("Product updated successfully", HttpStatus.OK, updatedProductResponse);
    }

}
