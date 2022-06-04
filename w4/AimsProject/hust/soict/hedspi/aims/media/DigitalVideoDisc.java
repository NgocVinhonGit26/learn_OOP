package media;

public class DigitalVideoDisc extends Media {
    private int length;
    private String director;

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public DigitalVideoDisc(String title, String category, String director, int length, float cost) {
        super(title, category, cost);
        this.director = director;
        this.length = length;
    }

    public DigitalVideoDisc(String title) {
        super(title);
    }

    public DigitalVideoDisc(String title, String category) {
        super(title, category);
    }

    public DigitalVideoDisc(String title, String category, String director) {
        super(title, category);
        this.director = director;
    }

    public DigitalVideoDisc(int id, String title, String category, int length, String director, float cost) {
        super(id, title, category, cost);
        this.director = director;
        this.length = length;
    }

    public void printInfo() {
        System.out.println("DVD: " + getId() + " - " + getTitle() + " - " + getCategory() + " - " + getDirector()
                + " - " + getLength()
                + " - " + getCost());

    }
}