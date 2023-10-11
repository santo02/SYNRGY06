package org.example.controller;

import org.example.model.food;
import org.example.model.order;
import org.example.service.invoice;
import org.example.view.General;
import org.example.view.errorAlert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import static org.example.utils.constants.*;

public class foodController {

    private final List<food> menuItems = new ArrayList<>();
    private final List<order> carts = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);
    private boolean isMenu = true;

//    OPTIONAL
    private Optional<food> getMenuFood(int choice) {
        if (choice >= 1 && choice <= menuItems.size()) {
            return Optional.of(menuItems.get(choice - 1));
        }
        return Optional.empty();
    }

    private Optional<order> getorderItem(food foodItem) {
        return carts.stream()
                .filter(orderItem -> orderItem.getFoodItem().equals(foodItem))
                .findFirst();
    }
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

        List<food> filteredMenu = menuItems.stream()
                .filter(food -> food.getPrice() <= 5000)
                .toList();

        filteredMenu.forEach(i -> System.out.println(i.getName()));

        while (isMenu){
            showMenu(menuItems);
            System.out.println("99. Bayar dan Pesan");
            System.out.println("0. Keluar aplikasi" + NEW_LINE);
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
                    menuItemNumber + ". " + item.getName() + ONE_TAB + PIPE +  ONE_TAB + item.getPrice()
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
        Optional<food> selectedFood = getMenuFood(choice);
//        jika menu sudah ada
        if (selectedFood.isPresent()) {
            food foodItem = selectedFood.get();
            System.out.println(foodItem.getName() + ONE_TAB + PIPE + foodItem.getPrice());
            System.out.println("(input 0 untuk kembali)");
            System.out.print("Qty =>  ");
            int qty = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Catatan : ");
            String varian = scanner.nextLine();
            if (qty == 0) {
                isMenu = true;
            } else {
                Optional<order> orderItem = getorderItem(foodItem);
//                jika sebelumnya udah ada
                if (orderItem.isPresent()){
                    order existingCart = orderItem.get();
                    existingCart.setQuantity(existingCart.getQuantity() + qty);
                    existingCart.setVarian(varian);
                }
//                jika belum
                else {
                    order neworderItem = new order(foodItem, qty, varian);
                    carts.add(neworderItem);
                }
            }
        }
    }

    public void payment() {
        double totalPrice = 0;
        int totalItem = 0;

        General.confirmAndPay();

        if (carts.isEmpty()) {
            General.menuNull();
        }

        for (order item : carts) {
            System.out.println(item.getFoodItem().getName() + DOUBLE_TAB + item.getQuantity() + ONE_TAB + item.getFoodItem().getPrice() + " | \t"+ item.getQuantity() * item.getFoodItem().getPrice() + "\t (" + item.getVarian() + ")");
            totalPrice += item.getFoodItem().getPrice() * item.getQuantity();
            totalItem += item.getQuantity();
        }
        System.out.println(THIN_LINE + " +");
        System.out.println("Total" + DOUBLE_TAB+ONE_TAB + totalItem + ONE_TAB + totalPrice + NEW_LINE);
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
