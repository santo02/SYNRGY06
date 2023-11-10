package com.challenge4.controller;

import com.challenge4.model.merchants;
import com.challenge4.response.ResponseHandler;
import com.challenge4.service.MerchantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/merchants")
public class MerchantController {
    @Autowired
    private MerchantService merchantService;

    private final static Logger logger =  LoggerFactory.getLogger(MerchantController.class);

    @PostMapping("add-merchant")
    public merchants add(@RequestBody merchants merchant){
        return merchantService.create(merchant);
    }

    @GetMapping("{id}")
    public merchants show(@PathVariable("id") UUID idMerchant){
        return merchantService.getById(idMerchant);
    }

    @GetMapping
    public List<merchants> getAll(){
        return merchantService.getAll();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") UUID idMerchant){
       try {
           merchants deletedMerchant = merchantService.delete(idMerchant);

           if (deletedMerchant != null) {
               return  ResponseHandler.generateResponse("Data berhasil di hapus!", HttpStatus.OK, deletedMerchant );
           } else {
               return  ResponseHandler.generateResponse("Data Tidak Ditemukan", HttpStatus.NOT_FOUND, null);
           }
       }catch (Exception e){
           return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
       }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> updateMerchant(@PathVariable UUID id, @RequestBody merchants updatedMerchant) {
       try {
           merchants updated = merchantService.updateMerchant(id, updatedMerchant);

           if (updated != null) {
               return ResponseHandler.generateResponse("Merchant berhasil di update", HttpStatus.OK, updated);
           } else {
               return ResponseEntity.notFound().build();
           }
       }catch (Exception e){
           return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
       }
    }

    @PatchMapping("/update/status/{id}")
    public ResponseEntity<Object> toggleMerchantStatus(@PathVariable UUID id) {
        merchants updated = merchantService.updateStatus(id);
        if (updated != null) {
            return ResponseHandler.generateResponse("Status Merchant berhasil di update", HttpStatus.OK, updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
