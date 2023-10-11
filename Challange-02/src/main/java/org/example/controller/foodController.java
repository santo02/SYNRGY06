package org.example.controller;

import org.example.model.food;
import org.example.model.cart;
import org.example.service.invoice;
import org.example.view.General;
import org.example.view.errorAlert;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class foodController {

    private final List<food> menuItems = new ArrayList<>();
    private final List<cart> carts = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);
    private boolean isMenu = true;

    public void home(){
        General.wellcome();
        listMenu();
    }

    public  void listMenu(){
        menuItems.add(new food("Nasi Goreng  ", 15000));
        menuItems.add(new food("Mie Goreng   ", 13000));
        menuItems.add(new food("Nasi + Ayam  ", 18000));
        menuItems.add(new food("Es Teh Manis ", 3000));
        menuItems.add(new food("Es Jeruk     ", 5000));

        while (isMenu){
            showMenu(menuItems);
            System.out.println("99. Bayar dan Pesan");
            System.out.println("0. Keluar aplikasi\n");
            System.out.print("=> ");
            int choice = scanner.nextInt();
            choiceHandle(choice);

            if (choice == 0) {
                General.closeApp();
                break; // Exit the loop and the application
            }
        }
    }

    public  void showMenu(List<food> menuItems){
        int menuItemNumber = 1;
        for (food item : menuItems) {
            System.out.println(
                    menuItemNumber + ". " + item.getName() + "\t" + "|\t" + item.getPrice()
            );
            menuItemNumber++;
        }
    }
    public  void choiceHandle(int choice){
        errorAlert  errorAlert = new errorAlert();
        if (choice == 0) {
            errorAlert.worngChoice();
        }
        if (choice >= 1 && choice <= menuItems.size()) {
            setQty(choice);
        } else if (choice != 99) {
            errorAlert.worngChoice();
        }
        if(choice == 99){
            payment();
        }
    }

    public void setQty(int choice) {

        food selectedFood = menuItems.get(choice - 1);
        System.out.println(selectedFood.getName() + "\t|" + selectedFood.getPrice());
        System.out.println("(input 0 untuk kembali)");
        System.out.print("Qty =>  ");
        int qty = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Catatan : ");
        String varian = scanner.nextLine();
        if (qty == 0) {
            isMenu=true;
        } else {
            boolean itemFound = false;
            for (cart cartItem : carts) {
                if (cartItem.getFoodItem().equals(selectedFood)) {
                    cartItem.setQuantity(cartItem.getQuantity() + qty);
                    cartItem.setVarian(varian);
                    itemFound = true;
                }
            }
            if (!itemFound) {
                cart cartItem = new cart(selectedFood, qty, varian);
                carts.add(cartItem);
            }
        }
    }

    public void payment() {
        double totalPrice = 0;
        int totalItem = 0;

        General.confirmAndPay();
//        if (carts.size() <= 0){
//            General.menuNull();
//        }

        if (carts.isEmpty()) {
            General.menuNull();
        }

        for (cart item : carts) {
            System.out.println(item.getFoodItem().getName() + "\t\t" + item.getQuantity() + "\t" + item.getFoodItem().getPrice() + " | \t"+ item.getQuantity() * item.getFoodItem().getPrice() + "\t (" + item.getVarian() + ")");
            totalPrice += item.getFoodItem().getPrice() * item.getQuantity();
            totalItem += item.getQuantity();
        }
        System.out.println("-------------------------\t+");
        System.out.println("Total\t\t\t" + totalItem + "\t" + totalPrice + "\n");
        General.menu2();
        System.out.print("=> ");

        int continueChoice = scanner.nextInt();
        if (continueChoice == 0) {
            General.closeApp();
            isMenu = false;
        }else if(continueChoice == 1) {
            General.confirmPayment();
            invoice invoice = new invoice();
            invoice.invoiceTxt(carts, totalPrice);
            isMenu= false;
        } else if (continueChoice == 2) {
            isMenu = true;
        } else {
            errorAlert  errorAlert = new errorAlert();
            errorAlert.worngChoice();
        }
    }



}
