package media;

import java.util.ArrayList;
import java.util.List;

public class Book extends Media {
    private List<String> authors = new ArrayList<String>();

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public void addAuthor(String authorName) {
        if (authors.contains(authorName) == false) {
            authors.add(authorName);
        } else
            return;
    }

    public void removeAuthor(String authorName) {
        if (authors.contains(authorName) == true) {
            authors.remove(authorName);
        } else
            return;
    }

    public Book(String title) {
        super(title);
    }

    public Book(String title, String category) {
        super(title, category);
    }

    public Book(String title, String category, List<String> authors) {
        super(title, category);
        this.authors = authors;
    }

    public Book(int id, String title, String category, float cost, String authors) {
        super(id, title, category, cost);
        addAuthor(authors);
    }

    public void printInfo() {
        System.out.println("Book: " + getId() + " - " + getTitle() + " - " + getCategory() + " - " + getAuthors()
                + " - " + getCost());
    }
}
