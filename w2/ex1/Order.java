import java.util.List;

public class Order {
    public static final int MAX_NUMBERS_ORDERED = 10;
    private DigitalVideoDisc itemOdered[] = new DigitalVideoDisc[MAX_NUMBERS_ORDERED];
    private int qtyOrdered = 0;

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
        // System.out.println(qtyOrdered);
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
}
