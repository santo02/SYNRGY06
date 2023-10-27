package com.challenge4.controller;

import com.challenge4.view.HomeView;
import com.challenge4.view.OrderView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class HomeController {

    @Autowired
    private MerchantController merchantController;
    @Autowired
    private ProductController productController;
    @Autowired
    private UserController userController;
    @Autowired
    private OrderController orderController;
    public  void home(){
        HomeView.Wellcome();
        HomeView.menuOption();
        SelectMainMenu();

    }
    private  void SelectMainMenu(){
        System.out.print("Pilih menu : ");
        Scanner scanner =  new Scanner(System.in);
        int mainMenuSelect = scanner.nextInt();
        if (mainMenuSelect == 1){
            merchantController.MerchantIndex();
        } else if (mainMenuSelect == 2) {
            productController.ProductIndex();
        } else if (mainMenuSelect == 3) {
            userController.userIndex();
        } else if (mainMenuSelect == 4){
            orderController.orderIndex();
        }else {
            HomeView.worngSelection();
            home();
        }
    }
}
