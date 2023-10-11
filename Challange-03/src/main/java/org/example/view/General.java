package org.example.view;

import lombok.Getter;

import static org.example.utils.constants.BOLD_LINE;
import static org.example.utils.constants.ONE_TAB;


@Getter
public class General {
    private General(){}

    public static void wellcome(){
        System.out.println(BOLD_LINE);
        System.out.println("Selamat datang di BinarFud");
        System.out.println(BOLD_LINE);
    }
    public static  void confirmAndPay(){
        System.out.println(BOLD_LINE);
        System.out.println(ONE_TAB + "Konfirmasi & Bayar");
        System.out.println(BOLD_LINE);
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
