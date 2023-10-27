package com.challenge4.controller;

import com.challenge4.model.merchants;
import com.challenge4.service.MerchantService;
import com.challenge4.view.HomeView;
import com.challenge4.view.MerchantView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Scanner;

@Component
@RestController
@RequestMapping("/merchant")
public class MerchantController {
    @Autowired
    private MerchantService merchantService;

    public  void MerchantIndex(){
        System.out.println("===================");
        System.out.println("List Merchant");
        System.out.println("===================");
        List<merchants> merchantsList = merchantService.getAll();
        MerchantView.showallMerchant(merchantsList);
        System.out.println("===================");
        MerchantView.MerchantMenu();
        SelectMerchantMenu();
    }

    private  void SelectMerchantMenu(){
        Scanner scanner = new Scanner(System.in);
        int menuSelect = scanner.nextInt();

        if (menuSelect == 1){
            addMerchant();
        } else if (menuSelect == 2) {
            editStatusMerchant();
        } else if (menuSelect == 3) {
            merchantOpen();
        }else{
            HomeView.worngSelection();
        }
    }

    private  void addMerchant(){
        merchants merchants = new merchants();
        Scanner scanner = new Scanner(System.in);
        System.out.println("======================");
        System.out.println("\tTAMBAH MERCHANT");
        System.out.println("======================");

        System.out.print("Masukkan Nama Merchant : ");
        String name = scanner.nextLine();
        System.out.print("Masukkan Lokasi Merchant : ");
        String location = scanner.nextLine();

        merchants.setName(name);
        merchants.setLocation(location);

        merchantService.create(merchants);
        MerchantIndex();
    }

    private void editStatusMerchant(){
        System.out.println("===================");
        System.out.println("Pilih Merchant");
        System.out.println("===================");
        List<merchants> merchantsList = merchantService.getAll();
        MerchantView.showallMerchant(merchantsList);
        System.out.println("===================");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan nama merchant :");
        String name = scanner.nextLine();

        System.out.print("T (Tutup) Atau B (Buka) : ");
        String statusInput = scanner.nextLine();

        boolean status;

        if (statusInput.equalsIgnoreCase("T") ||
                statusInput.equalsIgnoreCase("tutup") ){
            status = false;
        } else {
            status = true;
        }
        merchantService.editStatusByName(name, status);
        MerchantIndex();
    }

    private void merchantOpen(){
        System.out.println("======================");
        System.out.println("Merhcant Yang Buka ");
        System.out.println("======================");

        List<merchants> openMerchants =merchantService.findOpenMerchants();
        MerchantView.showMerchantOpen(openMerchants);
        System.out.println("======================");

        System.out.print("0 untuk Kembali :");
        Scanner scanner = new Scanner(System.in);
        int back = scanner.nextInt();
        if (back == 0){
            MerchantIndex();
        }
    }
}
