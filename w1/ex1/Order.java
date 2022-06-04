// package ex1;

public class Order {
    public static final int MAX_NUMBERS_ORDERED = 10;
    private DigitalVideoDisc itemOdered[] = new DigitalVideoDisc[MAX_NUMBERS_ORDERED];
    private int qtyOrdered = 0;

    public void addDigitalVideoDisc(DigitalVideoDisc disc) {
        if (qtyOrdered < MAX_NUMBERS_ORDERED) {
            itemOdered[qtyOrdered] = disc;

            System.out.println(itemOdered[qtyOrdered].getTitle());
            System.out.print("The disc has been added\n");
            qtyOrdered++;
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
}
