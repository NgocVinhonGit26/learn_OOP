import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GarbageCreator {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        String s = "";
        try {
            File fin = new File("C:\\Users\\ThinkPad\\Documents\\text.txt");
            Scanner myScaner = new Scanner(fin);
            while (myScaner.hasNextLine()) {
                s += myScaner.nextLine();
            }
            myScaner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error");
            e.printStackTrace();
        }
        System.out.println(s);
        System.out.println(System.currentTimeMillis() - start);
    }
}