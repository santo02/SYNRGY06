package com.challenge4.view;

import com.challenge4.model.OrderDetails;

import java.util.List;

public class OrderView {
    public static void orderMenu(){
        System.out.println("Silahkan pilih Menu :");
        System.out.println("1. Pesan ");
        System.out.println("2. Lihat Pesanan");
//        System.out.println("3. Hapus user" );
    }

    public static void showOrderByUser(List<OrderDetails> orderDetailsList) {
        for (int i = 0; i < orderDetailsList.size(); i++) {
            OrderDetails orderDetailList = orderDetailsList.get(i);
            System.out.print((i+1) + ". "
                    + "Nama product : " + orderDetailList.getProducts().getName()
                    + "("+ orderDetailList.getQuantity()+ ")"
                    + " Total Harga : " + orderDetailList.getTotalPrice()
                    + " Tanggal : " + orderDetailList.getOrders().getOrderTime());
        }
    }
}
