import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Food {

    private String name;
    private double price;

    public Food(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

class Cart {

    private Food foodItem;
    private int quantity;

    public Cart(Food foodItem, int quantity) {
        this.foodItem = foodItem;
        this.quantity = quantity;
    }

    public Food getFoodItem() {
        return foodItem;
    }
    public int getQuantity() {
        return quantity;
    }
}

public class BinarFud {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Food> menu = new ArrayList<>();
        List<Cart> cart = new ArrayList<>();
        double totalPrice = 0.0;

        menu.add(new Food("Nasi Goreng   |  ", 15.000));
        menu.add(new Food("Mie Goreng    |  ", 13.000));
        menu.add(new Food("Nasi + Ayam   |  ", 18.000));
        menu.add(new Food("Es Teh Manis  |  ", 3.000));
        menu.add(new Food("Es Jeruk      |  ", 5.000));

        while (true) {
            System.out.println("==========================");
            System.out.println("Selamat datang di BinarFud");
            System.out.println("==========================");
            int menuItemNumber = 1;
            System.out.println("Silahkan pilih makanan:");

            for (Food item : menu) {
                System.out.println(
                        menuItemNumber + ". " + item.getName() + item.getPrice() + "00"
                );
                menuItemNumber++;
            }

            System.out.println("99. Bayar dan Pesan");
            System.out.println("0. Keluar aplikasi");

            System.out.print("=>  ");
            int choice = scanner.nextInt();

            if (choice == 0) {
                break;
            }

            if (choice >= 1 && choice <= menu.size()) {
                System.out.print("Qty =>  ");
                int qty = scanner.nextInt();

                if (qty > 0) {
                    Food selectedFood = menu.get(choice - 1);
                    Cart cartItem = new Cart(selectedFood, qty);
                    cart.add(cartItem);
                }
            } else if (choice != 99) {
                System.out.println("Input tidak valid");
            }

            if (choice == 99) {
                System.out.println("==================");
                System.out.println("Konfirmasi & bayar");
                System.out.println("==================");
                int totalItem = 0;
                for (Cart item : cart) {
                    System.out.println(
                            item.getFoodItem().getName() +
                                    item.getQuantity() +
                                    "    |   " +
                                    item.getFoodItem().getPrice() *
                                            item.getQuantity() +
                                    "00"
                    );
                    totalPrice += item.getFoodItem().getPrice() * item.getQuantity();
                    totalItem += item.getQuantity();
                }
                System.out.println("------------------------------");
                System.out.println(
                        "Total           " + totalItem + "    Rp" + totalPrice + "00"
                );
                System.out.println("1. Konfirmasi dan Bayar");
                System.out.println("2. Kembali ke Menu Utama");
                System.out.println("0. Keluar Aplikasi");

                int checkoutChoice = scanner.nextInt();
                if (checkoutChoice == 1) {
                    // Handle payment confirmation
                    System.out.println(
                            "Terima kasih atas pesanan Anda. Pembayaran telah dikonfirmasi."
                    );

                    saveOrderToFile(cart, totalPrice);

                    cart.clear();
                    totalPrice = 0.0;
                    break;
                } else if (checkoutChoice == 2) {
                    // Return to the main menu
                } else if (checkoutChoice == 0) {
                    break; // Exit the application
                }
            }
        }
    }

    private static void saveOrderToFile(List<Cart> cart, double totalPrice) {
        try {
            int totalItem = 0;
            FileWriter writer = new FileWriter("struk.txt");
            writer.write("=============================\n");
            writer.write("Binarfud      \n");
            writer.write("=============================\n");
            writer.write("Terimakasih sudah memesan di Binarfud\n\n");
            writer.write("Dibawah ini adalah pesanan anda \n\n");
            for (Cart item : cart) {
                Food food = item.getFoodItem();
                int qty = item.getQuantity();
                double itemTotal = food.getPrice() * qty;
                writer.write(food.getName() + " " + qty + "   |" + itemTotal + "00\n");
                totalItem += item.getQuantity();
            }
            writer.write("-----------------------------\n");
            writer.write(
                    "Total           | " + totalItem + "      |" + totalPrice + "00\n\n"
            );

            writer.write("Pembayaran :     BinarCash\n\n");
            writer.write("=============================\n");
            writer.write("Simpan struk ini sebagai      \n");
            writer.write("bukti pembayaran      \n");
            writer.write("=============================\n");

            writer.close();
            System.out.println("Struk pembayaran telah disimpan di 'struk.txt'");
        } catch (IOException e) {
            System.out.println("Gagal menyimpan struk pembayaran ke file.");
        }
    }
}
