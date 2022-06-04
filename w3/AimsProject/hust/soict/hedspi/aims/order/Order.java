package order;

import disc.DigitalVideoDisc;
import utils.MyDate;

public class Order {
    public static final int MAX_NUMBERS_ORDERED = 10;
    private DigitalVideoDisc itemOdered[] = new DigitalVideoDisc[MAX_NUMBERS_ORDERED];
    private int qtyOrdered = 0;
    private MyDate dateOrdered = new MyDate();
    public static final int MAX_LIMITTED_ORDERED = 5;
    private static int nbOrders = 0;

    public void setDateOrder(MyDate dateOrder) {
        if (nbOrders < MAX_LIMITTED_ORDERED) {
            this.dateOrdered = dateOrder;
            System.out.println("The order has been added");
            // nbOrders++;
            if (nbOrders == MAX_LIMITTED_ORDERED - 1) {
                System.out.println("The list order is almost full");
            }
        } else {
            System.out.println("The list order is full");
        }
    }

    public Order() {
        if (nbOrders < MAX_LIMITTED_ORDERED) {
            nbOrders++;
        } else
            return;
    }

    public int getNbOrder() {
        return nbOrders;
    }

    public void addDigitalVideoDisc(DigitalVideoDisc disc) {
        if (qtyOrdered < MAX_NUMBERS_ORDERED) {
            itemOdered[qtyOrdered++] = disc;

            System.out.println(qtyOrdered);
            System.out.println(itemOdered[qtyOrdered - 1].getTitle());
            System.out.print("The disc has been added\n");

            if (qtyOrdered == MAX_NUMBERS_ORDERED - 1)
                System.out.print("The order is almost full\n");
        } else {
            System.out.print("The order is full\n");
        }
    }

    public void removeDigitalVideoDisc(DigitalVideoDisc disc) {
        for (int i = 0; i < MAX_NUMBERS_ORDERED; i++) {
            if (itemOdered[i] == disc) {
                for (int j = i; j < MAX_NUMBERS_ORDERED - 1; j++) {
                    itemOdered[j] = itemOdered[j + 1];
                }
            }
        }
    }

    public float totalCost() {
        float totalCost = 0;
        for (int i = 0; i < qtyOrdered; i++) {
            totalCost += itemOdered[i].getCost();
        }
        return totalCost;
    }

    public void addDigitalVideoDisc(DigitalVideoDisc[] dvdList) {
        for (int i = 0; i < dvdList.length; i++) {
            if (qtyOrdered >= MAX_NUMBERS_ORDERED) {
                System.out.print("The order is full\n");
                return;
            } else {
                itemOdered[qtyOrdered++] = dvdList[i];
                System.out.println(qtyOrdered);
                System.out.println(dvdList[i].getTitle());
                System.out.print("The disc has been added\n");
                if (qtyOrdered == MAX_NUMBERS_ORDERED - 1) {
                    System.out.print("The order is almost full\n");
                }
            }
        }
    }

    public void addDigitalVideoDisc(DigitalVideoDisc dvd1, DigitalVideoDisc dvd2) {
        if (qtyOrdered >= MAX_NUMBERS_ORDERED) {
            System.out.print("The order is full\n");
            return;
        }
        if (qtyOrdered == MAX_NUMBERS_ORDERED - 1) {
            itemOdered[qtyOrdered++] = dvd1;
            System.out.println(qtyOrdered);
            System.out.println(itemOdered[qtyOrdered - 1].getTitle());
            System.out.print("The disc has been added\n");
            System.out.print("The order is full\n");
        }
        if (qtyOrdered < MAX_NUMBERS_ORDERED - 1) {
            itemOdered[qtyOrdered++] = dvd1;
            System.out.println(qtyOrdered);
            System.out.println(itemOdered[qtyOrdered - 1].getTitle());
            System.out.print("The disc has been added\n");
            itemOdered[qtyOrdered++] = dvd2;
            System.out.println(qtyOrdered);
            System.out.println(itemOdered[qtyOrdered - 1].getTitle());
            System.out.print("The disc has been added\n");
            if (qtyOrdered == MAX_NUMBERS_ORDERED - 1) {
                System.out.print("The order is almost full\n");
            }
            if (qtyOrdered == MAX_NUMBERS_ORDERED) {
                System.out.print("The order is full\n");
            }
        }
    }

    public void printFull() {
        System.out.println("**************************Order***********************");
        System.out.println("Date: " + this.dateOrdered.getDay() + "/" + this.dateOrdered.getMonth() + "/"
                + this.dateOrdered.getYear());
        System.out.println("Ordered Item: ");
        for (int i = 0; i < qtyOrdered; i++) {
            System.out.println(i + 1 + ". " + "DVD - " + itemOdered[i].getTitle() + " - " + itemOdered[i].getCategory()
                    + " - "
                    + itemOdered[i].getDirector() + " - " + itemOdered[i].getLength() + ": " + itemOdered[i].getCost());
        }
        System.out.println("Total cost: " + this.totalCost());
        System.out.println("*****************************************************");
    }

    public DigitalVideoDisc getALuckyitem() {
        int min = 0;
        int max = qtyOrdered - 1;
        int range = max - min + 1;
        int lucky = (int) (Math.random() * range) + min;
        System.out.println("*** Lucky number is: " + lucky + " ***");
        return itemOdered[lucky - 1];
    }
}
