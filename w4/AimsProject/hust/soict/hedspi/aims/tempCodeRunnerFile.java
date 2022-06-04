import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import media.Book;
import media.DigitalVideoDisc;
import media.Media;
import order.Order;
import utils.MyDate;

public class Aims {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Scanner scan1 = new Scanner(System.in);
        int choose;
        Boolean check = true;
        Order order = null;
        ArrayList<Media> mediaList = new ArrayList<Media>();
        MyDate dateOrdered = new MyDate(26, 7, 2001);

        DigitalVideoDisc dvd1 = new DigitalVideoDisc(1, "The Lion King", "animation", 87, "Roger Allers", 19.95f);
        DigitalVideoDisc dvd2 = new DigitalVideoDisc(2, "Justice League", "superheroes", 240, "Zach Synder", 22.95f);
        DigitalVideoDisc dvd3 = new DigitalVideoDisc(3, "Up", "animation", 96, "Pete Docter", 14.5f);
        DigitalVideoDisc dvd4 = new DigitalVideoDisc(4, "The Incredibles", "animation", 115, "Brad Bird", 19.95f);

        Book book1 = new Book(5, "It", "horror", 12f, "Stephen King");
        Book book2 = new Book(6, "The Shining", "horror", 9f, "Stephen King");
        Book book3 = new Book(7, "Dragon", "horror", 15.5f, "Lovecraft");
        Book book4 = new Book(8, "The Pillow Book", "biography", 12f, "Sei Shonagon");

        Collections.addAll(mediaList, dvd1, dvd2, dvd3, dvd4, book1, book2, book3, book4);

        while (check) {
            showMenu();
            choose = scan.nextInt();
            switch (choose) {
                case 1: {
                    order = new Order();
                    order.setDateOrder(dateOrdered);
                    System.out.println("create order successfully");
                    break;
                }
                case 2: {
                    System.out.println("id: ");
                    int id = scan.nextInt();
                    boolean tmp = false;
                    for (Media media : mediaList) {
                        if (media.getId() == id) {
                            Add(order, media);
                            tmp = true;
                        }
                    }
                    if (tmp == false)
                        System.out.println("This id is not valid");
                    break;
                }
                case 3: {
                    System.out.println("id to remove:");
                    int idRm = scan.nextInt();
                    Delete(order, idRm);
                    break;
                }
                case 4: {
                    order.printFull();
                    break;
                }
                case 0: {
                    check = false;
                    break;
                }
            }
        }
        scan.close();
        scan1.close();
    }

    public static void showMenu() {
        System.out.println("\nOrder Management Application: ");
        System.out.println("----------------------------");
        System.out.println("1. Create new order");
        System.out.println("2. Add item to the order");
        System.out.println("3. Delete item by id");
        System.out.println("4. Display the items list of order");
        System.out.println("0. Exit");
        System.out.println("----------------------------");
        System.out.println("Please choose a number: 0-1-2-3-4");
    }

    public static void Add(Order order, Media item) {
        order.addMedia(item);
    }

    public static void Delete(Order order, int id) {
        order.removeMedia(id);
    }
}