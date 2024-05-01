package extendeddemo.model;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "publisher")
public class Publisher {
    @Id
    private int id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinTable(name = "book_publisher",
            joinColumns = {@JoinColumn(name = "publisher_id")},
            inverseJoinColumns = {@JoinColumn(name = "book_id")})
    private Set<Book> books = new HashSet<>();

    public Publisher() {}

    public Publisher(String name) {
        this.name = name;
    }

    public Publisher(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    public void addBook(Book book) {
        this.books.add(book);
        book.getPublishers().add(this);
    }
}
