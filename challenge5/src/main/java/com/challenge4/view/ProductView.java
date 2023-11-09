package com.challenge4.view;

import com.challenge4.model.products;

import java.util.List;

public class ProductView {

    public static void ProductMenu(){
        System.out.println("Silahkan pilih Menu :");
        System.out.println("1. Tambah Product");
        System.out.println("2. Edit Product");
        System.out.println("3. Hapus Product" );
        System.out.println("4. Tampilkan Product yang tersedia");
    }

    public static void showAllproduct(List<products> productssList) {
        for (int i = 0; i < productssList.size(); i++) {
            products product = productssList.get(i);
            System.out.print((i + 1) + ". ");
            product.print();
        }
    }
}
