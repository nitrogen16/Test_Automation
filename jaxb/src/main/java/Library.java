import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

public class Library {

    @XmlAttribute
    private int id;
    
    @XmlElement(name = "Book")
    private List<Book> books = new ArrayList<Book>();

    public Library() {
    }

    public Library(List<Book> books) {
        this.books = books;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Library{" +
                "id=" + id +
                ", books=" + books +
                '}';
    }
}
