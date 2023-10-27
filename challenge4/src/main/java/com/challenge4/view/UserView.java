package com.challenge4.view;

import com.challenge4.model.products;
import com.challenge4.model.users;

import java.util.List;

public class UserView {
    public static void userMenu(){
        System.out.println("Silahkan pilih Menu :");
        System.out.println("1. Tambah user");
        System.out.println("2. Edit user");
        System.out.println("3. Hapus user" );
    }

    public static void showAllUsers(List<users> usersList) {
        for (int i = 0; i < usersList.size(); i++) {
            users users= usersList.get(i);
            System.out.print((i + 1) + ". ");
            users.print();
        }
    }
}

