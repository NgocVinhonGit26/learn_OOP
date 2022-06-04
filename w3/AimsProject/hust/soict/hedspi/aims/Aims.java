import disc.DigitalVideoDisc;
import order.Order;
import utils.MyDate;

public class Aims {
    public static void main(String[] args) {
        Order anOrder = new Order();

        DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King");
        dvd1.setCategory("Animation");
        dvd1.setCost(19.95f);
        dvd1.setDirector("Roger Allers");
        dvd1.setLength(87);

        anOrder.addDigitalVideoDisc(dvd1);

        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Stat Wars");
        dvd2.setCategory("Science Fiction");
        dvd2.setCost(24.95f);
        dvd2.setDirector("George Lucas");
        dvd2.setLength(124);

        anOrder.addDigitalVideoDisc(dvd2);

        DigitalVideoDisc dvd3 = new DigitalVideoDisc("Alddin");
        dvd3.setCategory("Animation");
        dvd3.setCost(18.99f);
        dvd3.setDirector("John Musker");
        dvd3.setLength(90);

        anOrder.addDigitalVideoDisc(dvd3);

        DigitalVideoDisc list[] = new DigitalVideoDisc[3];
        list[0] = dvd1;
        list[1] = dvd2;
        list[2] = dvd3;

        anOrder.addDigitalVideoDisc(list);
        anOrder.addDigitalVideoDisc(list);
        // anOrder.addDigitalVideoDisc(dvd1, dvd2);
        // anOrder.addDigitalVideoDisc(dvd1);
        // anOrder.addDigitalVideoDisc(dvd3, dvd2);

        // System.out.print("Total Cost is: ");
        // System.out.println(anOrder.totalCost());
        MyDate stringDate = new MyDate();
        stringDate.setDay(26);
        stringDate.setMonth(7);
        stringDate.setYear(2001);
        anOrder.setDateOrder(stringDate);
        anOrder.printFull();
        System.out.println(anOrder.getNbOrder());

        Order anOrder2 = new Order();
        anOrder2.setDateOrder(stringDate);
        System.out.println(anOrder.getNbOrder());
    }

}