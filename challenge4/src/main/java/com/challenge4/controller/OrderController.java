package com.challenge4.controller;

import com.challenge4.model.OrderDetails;
import com.challenge4.model.products;
import com.challenge4.model.users;
import com.challenge4.repository.OrderDetailRepository;
import com.challenge4.repository.OrderRepository;
import com.challenge4.repository.ProductRepository;
import com.challenge4.repository.UserRepository;
import com.challenge4.service.OrderService;
import com.challenge4.view.HomeView;
import com.challenge4.view.OrderView;
import com.challenge4.view.ProductView;
import com.challenge4.view.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

@Component
@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private ProductRepository productRepository;

    public void orderIndex() {
        System.out.println("===================");
        System.out.println("\tOrder Menu");
        System.out.println("===================");
        OrderView.orderMenu();
        selectedMenu();
    }

    private void selectedMenu() {
        Scanner scanner = new Scanner(System.in);
        int menuSelect = scanner.nextInt();

        if (menuSelect == 1){
            orderProduct();
        } else if (menuSelect == 2) {
           showOrdered();
        } else{
            HomeView.worngSelection();
        }
    }


    private void showOrdered() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("======================");
        System.out.println("\tPILIH USER");
        System.out.println("======================");
        List<users> user = userRepository.findAll();
        System.out.println("Daftar User:");
        UserView.showAllUsers(user);
        System.out.print("Pilih User : ");

        int userSelected = scanner.nextInt();
        scanner.nextLine();

        if (userSelected < 1 || userSelected > user.size()) {
            System.out.println("Pilihan Tidak valid.");
        } else {
            users selectedUser = user.get(userSelected - 1);
            List<OrderDetails> orderDetailsList =orderService.getOrderDetailsForUser(selectedUser.getId());

            if (orderDetailsList.isEmpty()) {
                System.out.println("Belum ada orderan...");
            } else {
                OrderView.showOrderByUser(orderDetailsList);
            }

        }
    }


    private void orderProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("======================");
        System.out.println("\tPILIH USER");
        System.out.println("======================");
        List<users> user = userRepository.findAll();
        System.out.println("Daftar User:");
        UserView.showAllUsers(user);
        System.out.print("Pilih User : ");

        int userSelected = scanner.nextInt();
        scanner.nextLine();

        if (userSelected < 1 || userSelected > user.size()) {
            System.out.println("Pilihan Tidak valid.");
        } else {
            List<products> product = productRepository.findAll();
            System.out.println("Daftar Produk:");
            ProductView.showAllproduct(product);
            System.out.print("Pilih produk : ");

            int productSelected = scanner.nextInt();
            scanner.nextLine();
            if (productSelected < 1 || productSelected > product.size()) {
                System.out.println("Pilihan Tidak valid.");
            } else {
                System.out.println("Masukkan Destination Address : ");
                String address = scanner.nextLine();
                System.out.println("Masukkan Jumlah product : ");
                int quantity = scanner.nextInt();
                OrderDetails orderDetail =new OrderDetails();
                orderDetail.setQuantity(quantity);
                orderDetail.setProducts(product.get(productSelected-1));
                orderDetail.setTotalPrice(product.get(productSelected -1).getPrice() * quantity);

                List<OrderDetails> orderDetails = new ArrayList<>();
                orderDetails.add(orderDetail);

                orderService.orderProduct(user.get(userSelected-1), address, orderDetails);
            orderIndex();
            }
        }
    }

}
