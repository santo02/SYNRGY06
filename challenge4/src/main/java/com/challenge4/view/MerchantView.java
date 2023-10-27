package com.challenge4.view;

import com.challenge4.model.merchants;

import java.util.List;

public class MerchantView {

    public static void showallMerchant(List<merchants> merchantList){
        merchantList.forEach(merchants::print);
    }
    public static void showMerchantOpen(List<merchants> merchantList){

        merchantList.forEach(merchants::print);
    }
    public static void MerchantMenu(){
        System.out.println("Silahkan pilih Menu :");
        System.out.println("1. Tambah Merchant");
        System.out.println("2. Edit Status");
        System.out.println("3. Tampilkan yang sedang buka" );
    }
}
