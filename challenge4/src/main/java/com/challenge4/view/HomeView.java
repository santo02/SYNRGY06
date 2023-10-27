package com.challenge4.view;

public class HomeView {
    public static void Wellcome(){
        System.out.println("Selamat datang di Binarfud");
    }
    public static void menuOption(){
        System.out.println("Silahkan pilih menu :");
        System.out.println("1. Kelola Merchat");
        System.out.println("2. Kelola produk");
        System.out.println("3. Kelola User");
        System.out.println("4. Kelola Pesanan");
    }

    public  static void worngSelection(){
        System.out.println("Pilihan yang anda masukkan tidak tersedia!");
    }


}
