package order;

import java.util.ArrayList;

import media.Media;
import utils.MyDate;

public class Order {
    public static final int MAX_LIMITTED_ORDERED = 5;
    private static int nbOrders = 0;
    public static final int MAX_NUMBERS_ORDERED = 10;
    private ArrayList<Media> itemOdered = new ArrayList<Media>();
    private int qtyOrdered = 0;
    private MyDate dateOrdered = new MyDate();

    public ArrayList<Media> getItemOdered() {
        return itemOdered;
    }

    public void setItemOdered(ArrayList<Media> itemOdered) {
        this.itemOdered = itemOdered;
    }

    public int getqtyOrdered() {
        return this.qtyOrdered;
    }

    public void setDateOrder(MyDate dateOrder) {
        this.dateOrdered = dateOrder;

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

    public void addMedia(Media media) {
        if (itemOdered.size() < MAX_LIMITTED_ORDERED) {
            qtyOrdered++;
            itemOdered.add(media);
            System.out.println("add media successfully");
        } else {
            System.out.println("The list order is full");
        }
    }

    public void removeMedia(int id) {
        boolean check = false;
        check = itemOdered.removeIf((media) -> {
            qtyOrdered--;
            return media.getId() == id;
        });
        if (check == false)
            System.out.println("can't find media");
    }

    public float totalCost() {
        float totalCost = 0;
        for (Media media : itemOdered) {
            totalCost += media.getCost();
        }
        return totalCost;
    }

    public void printFull() {
        System.out.println("**************************Order***********************");
        System.out.println("Date: " + this.dateOrdered.getDay() + "/" + this.dateOrdered.getMonth() + "/"
                + this.dateOrdered.getYear());
        System.out.println("Ordered Item: ");
        for (Media media : itemOdered) {
            media.printInfo();
        }
        System.out.println("Total cost: " + this.totalCost());
        System.out.println("*****************************************************");
    }

    public Media getALuckyitem() {
        int min = 0;
        int max = qtyOrdered - 1;
        int range = max - min + 1;
        int lucky = (int) (Math.random() * range) + min;
        System.out.println("*** Lucky number is: " + lucky + " ***");
        return itemOdered.get(lucky);
    }
}