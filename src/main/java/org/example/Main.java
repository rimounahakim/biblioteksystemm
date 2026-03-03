package org.example;

import org.example.dao.BokDAO;
import org.example.dao.UserDAO;
import org.example.model.User;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        UserDAO userDAO = new UserDAO();
        BokDAO bokDAO = new BokDAO();

        System.out.println("=== Login ===");

        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        User user = userDAO.login(username, password);

        if (user == null) {
            System.out.println("Fel användarnamn eller lösenord.");
            return;
        }

        System.out.println("Welcome " + user.getName());


        // MENU LOOP
        while (true) {

            System.out.println("\n1. Visa alla böcker");
            System.out.println("2. Sök bok");
            System.out.println("3. Uppdatera profil");
            System.out.println("4. Låna bok");
            System.out.println("5. Mina lån");
            System.out.println("6. Avsluta");

            System.out.print("Välj: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    bokDAO.hamtaAllaBocker()
                            .forEach(System.out::println);
                    break;

                case 2:
                    System.out.print("Sökord: ");
                    String keyword = scanner.nextLine();

                    bokDAO.sokBok(keyword)
                            .forEach(System.out::println);
                    break;

                case 3:
                    System.out.print("Nytt namn: ");
                    String newName = scanner.nextLine();

                    System.out.print("Ny email: ");
                    String newEmail = scanner.nextLine();

                    userDAO.updateProfile(user.getId(), newName, newEmail);
                    System.out.println("Profil uppdaterad.");
                    break;

                case 4:
                    System.out.print("Ange bok ID: ");
                    int bookId = scanner.nextInt();
                    scanner.nextLine();

                    bokDAO.lanaBok(bookId, user.getId());
                    break;

                case 5:
                    bokDAO.visaMinaLan(user.getId());
                    break;

                case 6:
                    System.out.println("Program avslutat.");
                    return;

                default:
                    System.out.println("Fel val.");
            }
        }
    }
}