package com.challenge4.controller;

import com.challenge4.model.merchants;
import com.challenge4.model.products;
import com.challenge4.model.users;
import com.challenge4.repository.UserRepository;
import com.challenge4.service.UserService;
import com.challenge4.view.HomeView;
import com.challenge4.view.ProductView;
import com.challenge4.view.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Scanner;

import static org.apache.logging.log4j.util.LambdaUtil.getAll;
@Component
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;
    public void userIndex() {
        System.out.println("===================");
        System.out.println("List User Terdaftar");
        System.out.println("===================");
        List<users> usersList = userService.getAll();
        UserView.showAllUsers(usersList);
        System.out.println("===================");
        UserView.userMenu();
        SelectUserMenu();
    }

    private void SelectUserMenu() {
        Scanner scanner = new Scanner(System.in);
        int menuSelect = scanner.nextInt();

        if (menuSelect == 1){
            addUser();
        } else if (menuSelect == 2) {
            editUser();
        } else if (menuSelect == 3) {
            deleteUser();
        }else{
            HomeView.worngSelection();
        }
    }

    private void deleteUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("======================");
        System.out.println("\tHAPUS USER");
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
            System.out.print("Anda yakin ingin menghapus user ini? (Y/N): ");
            String confirmation = scanner.next();

            if (confirmation.equalsIgnoreCase("Y")) {
                userRepository.delete(selectedUser);
                System.out.println("User berhasil dihapus.");
            } else {
                System.out.println("Penghapusan dibatalkan.");
            }

            userIndex();
        }
    }

    private void editUser() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("===================");
        System.out.println("List User Terdaftar");
        System.out.println("===================");
        List<users> usersList = userService.getAll();
        UserView.showAllUsers(usersList);
        System.out.println("===================");
        System.out.print("Silahkan pilih user : ");

        int userSelected = scanner.nextInt();
        scanner.nextLine();
        if (userSelected < 1 || userSelected > usersList.size()) {
            System.out.println("Pilihan Tidak valid.");
        } else {
            users selectedUser = usersList.get(userSelected - 1);

            System.out.print("Masukkan Nama User Baru (kosongkan jika tidak ingin mengubah nama): ");
            String newName = scanner.nextLine();

            System.out.print("Masukkan  Email User Baru (kosongkan jika tidak ingin mengubah email): ");
            String newEmail = scanner.nextLine();

            System.out.print("Masukkan  Password User Baru (kosongkan jika tidak ingin mengubah  password): ");
            String newPassword = scanner.nextLine();



            if (!newName.isEmpty()) {
                selectedUser.setName(newName);
            }

            if (!newEmail.isEmpty()) {
                selectedUser.setEmail(newEmail);
            }
            if (!newPassword.isEmpty()){
            selectedUser.setPassword(newPassword);
            }

            users update = userRepository.save(selectedUser);
            if (update != null){
                System.out.println("Berhasil Mengupadate User");
            }else {
                System.out.println("Gagal Mengupdate User");
            }
            userIndex();
        }
    }

    private void addUser() {
        users user = new users();
        Scanner scanner = new Scanner(System.in);
        System.out.println("======================");
        System.out.println("\tTAMBAH USER");
        System.out.println("======================");

        System.out.print("Masukkan Nama  : ");
        String name = scanner.nextLine();
        System.out.print("Masukkan email : ");
        String email = scanner.nextLine();
        System.out.print("Masukkan Password : ");
        String password = scanner.nextLine();


        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);

        userService.create(user);
        userIndex();
    }
}
