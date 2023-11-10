package com.challenge4.service;

import com.challenge4.model.merchants;
import com.challenge4.repository.MerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MerchantService {
    @Autowired
    MerchantRepository merchantReopsitory;
    public List<merchants> getAll(){
        return merchantReopsitory.findAll();
    }
    public merchants create(merchants merchant){
        merchant.setOpen(true);
        return merchantReopsitory.save(merchant);
    }

    public void editStatusByName(String name, boolean isOpen) {
        merchantReopsitory.editStatusMerchant(name, isOpen);
    }

    public  List<merchants> findOpenMerchants(){
        return  merchantReopsitory.findByIsOpenTrue();
    }

    public merchants getById(UUID idMerchant) {
        Optional<merchants> merchantOptional = merchantReopsitory.findById(idMerchant);
        if(merchantOptional.isEmpty()){
            return null;
        }
        return merchantOptional.get();

    }

    public merchants delete(UUID idMerchant) {
        Optional<merchants> merchantOptional = merchantReopsitory.findById(idMerchant);
        if(merchantOptional.isEmpty()){
            return null;
        }
        merchants deleteMerchants = merchantOptional.get();
        merchantReopsitory.deleteById(idMerchant);
        return deleteMerchants;
    }

    public merchants updateMerchant(UUID id, merchants updatedMerchant) {
        return merchantReopsitory.findById(id)
                .map(existingMerchant -> {
                    if (updatedMerchant.getName() != null) {
                        existingMerchant.setName(updatedMerchant.getName());
                    }
                    if (updatedMerchant.getLocation() != null) {
                        existingMerchant.setLocation(updatedMerchant.getLocation());
                    }

                    return merchantReopsitory.save(existingMerchant);
                })
                .orElse(null); // Handle the case where the merchant with the given ID is not found
    }
    public merchants updateStatus(UUID id) {
        return merchantReopsitory.findById(id)
                .map(existingMerchant -> {
                    existingMerchant.setOpen(!existingMerchant.isOpen()); // Toggle the status
                    return merchantReopsitory.save(existingMerchant);
                })
                .orElse(null);
    }
}
