package org.example.view;

import lombok.Getter;



@Getter
public class General {
    private General(){}

    public static void wellcome(){
        System.out.println("==========================");
        System.out.println("Selamat datang di BinarFud");
        System.out.println("==========================");
    }
    public static  void confirmAndPay(){
        System.out.println("=========================");
        System.out.println("\tKonfirmasi & Bayar");
        System.out.println("=========================");
    }

    public static void menu2(){
        System.out.println("1. Konfirmasi & Bayar");
        System.out.println("2. Kembali ke menu utama");
        System.out.println("0. Keluar dari aplikasi");
    }

    public static  void confirmPayment(){
        System.out.println(" Terima kasih atas pesanan Anda. Pembayaran telah dikonfirmasi.");
    }

    public static void closeApp(){
        System.out.println("Terima kasih! Keluar dari aplikasi.");
    }

    public static void menuNull(){
        System.out.println("Belum ada Menu yang Dipilih");
    }
}
