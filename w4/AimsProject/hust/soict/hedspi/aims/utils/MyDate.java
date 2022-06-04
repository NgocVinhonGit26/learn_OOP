package utils;

import java.util.Scanner;

public class MyDate {
    private int day;
    private int month;
    private int year;

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public MyDate(int day, int month, int year) {
        super();
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public MyDate() {
        super();
    }

    public MyDate(String strDate) {
        String list[] = strDate.split(" ");
        String months[] = { "January", "Feburary", "March", "April", "May", "June", "July", "August", "September",
                "October", "November", "December" };
        // set day
        if (list[1].equals("1st") == true) {
            this.day = 1;
        } else if (list[1].equals("2nd") == true) {
            this.day = 2;
        } else if (list[1].equals("3rd") == true) {
            this.day = 3;
        } else {
            String days[] = list[1].split("th");
            this.day = Integer.parseInt(days[0]);
        }

        // set month
        for (int i = 0; i < 12; i++) {
            if (list[0].equals(months[i]) == true) {
                this.month = i + 1;
                break;
            }
        }
        // set year
        this.year = Integer.parseInt(list[2]);
    }

    public void accept() {
        System.out.println("enter date:");
        Scanner keyboard = new Scanner(System.in);
        String date = keyboard.nextLine();
        String ele[] = date.split(" ");
        String strMonth = ele[0];
        String strDay = ele[1];
        String strYear = ele[2];
        String months[] = { "January", "Feburary", "March", "April", "May", "June", "July", "August", "September",
                "October", "November", "December" };
        int i;
        for (i = 0; i < 12; i++) {
            if (strMonth.equals(months[i])) {
                this.month = i + 1;
                break;
            }
        }

        // set year
        int year = Integer.parseInt(strYear);
        this.year = year;

        // set day
        if (strDay.equals("1st") == true) {
            this.day = 1;
        } else if (strDay.equals("2nd") == true) {
            this.day = 2;
        } else if (strDay.equals("3rd") == true) {
            this.day = 3;
        } else {
            String days[] = strDay.split("th");
            this.day = Integer.parseInt(days[0]);
        }

    }
}
