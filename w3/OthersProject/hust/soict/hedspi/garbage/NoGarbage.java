import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class NoGarbage {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        StringBuffer sb = new StringBuffer();

        try {
            Scanner scan = new Scanner(new FileReader("C:\\Users\\ThinkPad\\Documents\\text.txt"));
            while (scan.hasNextLine()) {
                sb.append(scan.nextLine());
            }
            scan.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error");
            e.printStackTrace();
        }
        System.out.println(sb);
        System.out.println(System.currentTimeMillis() - start);

    }
}