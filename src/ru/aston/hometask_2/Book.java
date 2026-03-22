import java.util.Objects;

public class Book {
    private final String title;
    private final String author;
    private final int pages;
    private final int year;

    public Book(String title, String author, int pages, int year) {
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.year = year;
    }

    public int getPages() { return pages; }
    public int getYear() { return year; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book b = (Book) o;
        return pages == b.pages && year == b.year &&
                Objects.equals(title, b.title) && Objects.equals(author, b.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, pages, year);
    }

    @Override
    public String toString() {
        return title + " Автор: " + author + " (" + year + ", " + pages + "стр.)";
    }
}