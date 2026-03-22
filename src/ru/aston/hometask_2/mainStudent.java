import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class mainStudent {
    public static void main(String[] args) throws IOException {
        List<Student> students = Files.lines(Paths.get("C:\\Users\\qwerty\\IdeaProjects\\Hello\\Task 2\\students.txt"))
                .filter(s -> !s.trim().isEmpty())
                .map(mainStudent::parseStudent)
                .collect(Collectors.toList());

        // Один стрим без промежуточных переменных
        students.stream()
                .peek(System.out::println)
                .map(Student::getBooks)
                .flatMap(List::stream)
                .filter(b -> (b.getYear() > 2000))
                .distinct()

                .sorted(Comparator.comparingInt(Book::getPages))
                .limit(3)
                .map(Book::getYear)
                .findFirst() //возвращает Optional<Integer>
                .ifPresentOrElse(
                        y -> System.out.println("Год findFirst(): " + y),
                        () -> System.out.println("Таких книг нет")
                );
    }

    private static Student parseStudent(String line) {
        String[] parts = line.split(";", 2);
        String name = parts[0].trim();
        List<Book> books = new ArrayList<>();
        if (parts.length > 1 && !parts[1].trim().isEmpty()) {
            String[] items = parts[1].split(",");
            for (String it : items) {
                String[] f = it.split("\\|");
                if (f.length >= 4) {
                    String title = f[0].trim();
                    String author = f[1].trim();
                    int pages = Integer.parseInt(f[2].trim());
                    int year = Integer.parseInt(f[3].trim());
                    books.add(new Book(title, author, pages, year));
                }
            }
        }
        return new Student(name, books);

    }

}