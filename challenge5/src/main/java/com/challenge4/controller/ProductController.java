package com.challenge4.controller;

import com.challenge4.model.merchants;
import com.challenge4.model.products;
import com.challenge4.repository.MerchantRepository;
import com.challenge4.repository.ProductRepository;
import com.challenge4.service.ProductService;
import com.challenge4.view.HomeView;
import com.challenge4.view.MerchantView;
import com.challenge4.view.ProductView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Scanner;

@Component
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private MerchantRepository merchantRepository;

    @Autowired
    private ProductRepository productRepository;

    private final static Logger logger =  LoggerFactory.getLogger(MerchantController.class);
    public void ProductIndex(){
        System.out.println("===================");
        System.out.println("List Product");
        System.out.println("===================");
        List<products> productsList = productService.getAll();
        ProductView.showAllproduct(productsList);
        System.out.println("===================");
        ProductView.ProductMenu();
        SelectProductMenu();
    }
    private  void SelectProductMenu(){
        Scanner scanner = new Scanner(System.in);
        int menuSelect = scanner.nextInt();

        if (menuSelect == 1){
            addProduct();
        } else if (menuSelect == 2) {
            editProduct();
        } else if (menuSelect == 3) {
            deleteProduct();
        } else if (menuSelect == 4) {
            productReady();
        } else if (menuSelect == 0) {

        }else{
            HomeView.worngSelection();
        }
    }

    private void productReady() {
        System.out.println("=======================================");
        System.out.println("\tDAFTAR PRODUCT YANG TERSEDIA");
        System.out.println("=======================================");
        List<products> product = productRepository.findAll();
        System.out.println("Daftar Produk:");
        ProductView.showAllproduct(product);
        Scanner scanner = new Scanner(System.in);
        System.out.println("0 untuk Kembali : ");
        int back = scanner.nextInt();

        if(back == 0){
            ProductIndex();
        }

    }

    private void addProduct(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("======================");
        System.out.println("\tTAMBAH PRODUCT");
        System.out.println("======================");
        List<merchants> merchant = merchantRepository.findAll();
        System.out.println("List of Merchants:");
        int merchantNumber = 1;
        for (merchants m : merchant) {
            System.out.println(merchantNumber + ". " + m.getName());
            merchantNumber++;
        }

        System.out.print("Pilih Merchant: ");
        int selectedMerchantNumber = scanner.nextInt();
        scanner.nextLine();
        if (selectedMerchantNumber < 1 || selectedMerchantNumber > merchant.size()) {
            System.out.println("Pilihan Tidak valid.");
        } else {
            merchants selectedMerchant = merchant.get(selectedMerchantNumber - 1);
            System.out.println("Masukkan Nama Product : ");
            String name = scanner.nextLine();

            System.out.println("Masukkan Harga Product : ");
            Double price = Double.parseDouble(scanner.nextLine());

           products addNewProduct = productService.addProduct(name, price, selectedMerchant);
           if (addNewProduct != null){
               System.out.println("Berhasil Menambahkan produt!");
           }else {
               System.out.println("Gagal Menambahkan produt!");
           }
           ProductIndex();
        }


    }
    private void editProduct(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("======================");
        System.out.println("\tEDIT PRODUCT");
        System.out.println("======================");
        List<products> product = productRepository.findAll();
        System.out.println("Daftar Produk:");
        ProductView.showAllproduct(product);
        System.out.print("Pilih produk : ");
        int productSelected = scanner.nextInt();
        scanner.nextLine();
        if (productSelected < 1 || productSelected > product.size()) {
            System.out.println("Pilihan Tidak valid.");
        } else {
            products selectedProduct = product.get(productSelected - 1);

            System.out.print("Masukkan Nama Product Baru (kosongkan jika tidak ingin mengubah nama): ");
            String newName = scanner.nextLine();

            System.out.print("Masukkan Harga Product Baru (kosongkan jika tidak ingin mengubah harga): ");
            String priceInput = scanner.nextLine();

            if (!newName.isEmpty()) {
                selectedProduct.setName(newName);
            }

            if (!priceInput.isEmpty()) {
                double newPrice = Double.parseDouble(priceInput);
                selectedProduct.setPrice(newPrice);
            }

            products update = productRepository.save(selectedProduct);
            if (update != null){
                System.out.println("Berhasil Mengupadate Produk");
            }else {
                System.out.println("Gagal Mengupdate Produk");
            }
            ProductIndex();
        }
    }

    private void deleteProduct(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("======================");
        System.out.println("\tHAPUS PRODUCT");
        System.out.println("======================");
        List<products> product = productRepository.findAll();
        System.out.println("Daftar Produk:");
        ProductView.showAllproduct(product);
        System.out.print("Pilih produk : ");
        int productSelected = scanner.nextInt();
        scanner.nextLine();

        if (productSelected < 1 || productSelected > product.size()) {
            System.out.println("Pilihan Tidak valid.");
        } else {
            products selectedProduct = product.get(productSelected - 1);
            System.out.print("Anda yakin ingin menghapus produk ini? (Y/N): ");
            String confirmation = scanner.next();

            if (confirmation.equalsIgnoreCase("Y")) {
                // Delete the selected product from the database.
                productRepository.delete(selectedProduct);
                System.out.println("Produk berhasil dihapus.");
            } else {
                System.out.println("Penghapusan dibatalkan.");
            }

            ProductIndex();
        }
    }

}
