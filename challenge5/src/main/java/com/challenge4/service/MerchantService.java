package com.challenge4.service;

import com.challenge4.model.merchants;
import com.challenge4.repository.MerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
