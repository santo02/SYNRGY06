package org.example.service;

import org.example.model.order;
import org.example.model.food;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public  class invoice {
    public void invoiceTxt(List<order> cart, double totalPrice) {
        try {
            int totalItem = 0;
            FileWriter writer = new FileWriter( "invoice.txt");
            writer.write("=============================\n");
            writer.write("Binarfud      \n");
            writer.write("=============================\n");
            writer.write("Terimakasih sudah memesan di Binarfud\n\n");
            writer.write("Dibawah ini adalah pesanan anda \n\n");
            for (order item : cart) {
                food food = item.getFoodItem();
                int qty = item.getQuantity();
                double itemTotal = food.getPrice() * qty;
                writer.write(food.getName() + "\t" + qty + "|\t"+ food.getPrice() +"\t|" + itemTotal +   "\t(" + item.getVarian() + ")\n");
                totalItem += item.getQuantity();
            }
            writer.write("-----------------------------\n");
            writer.write(
                    "Total\t\t\t|" + totalItem + "\t\t|" + totalPrice + "\n\n"
            );

            writer.write("Pembayaran :     BinarCash\n\n");
            writer.write("=============================\n");
            writer.write("Simpan struk ini sebagai      \n");
            writer.write("bukti pembayaran      \n");
            writer.write("=============================\n");

            writer.close();
            System.out.println("Struk pembayaran telah disimpan di 'invoice.txt'");
        } catch (
                IOException e) {
            System.out.println("Gagal menyimpan struk pembayaran ke file.");
        }
    }

}