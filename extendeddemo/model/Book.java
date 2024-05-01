package extendeddemo.model;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "book")
public class Book {
    @Id
    private int id;

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @ManyToMany(mappedBy = "books", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private Set<Publisher> publishers = new HashSet<>();

    public Book() {}

    public Book(String title) {
        this.title = title;
    }

    public Book(int id, String title) {
        this.id = id;
        this.title = title;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Publisher> getPublishers() {
        return publishers;
    }

    public void setPublishers(Set<Publisher> publishers) {
        this.publishers = publishers;
    }

    public void addPublisher(Publisher publisher) {
        this.publishers.add(publisher);
        publisher.getBooks().add(this);
    }
}
