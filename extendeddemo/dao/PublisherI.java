package extendeddemo.dao;


import extendeddemo.model.Publisher;
import java.util.List;

public interface PublisherI {
    void createPublisher(Publisher publisher);
    List<Publisher> getAllPublishers();
    Publisher getPublisherById(int publisherId);
}
