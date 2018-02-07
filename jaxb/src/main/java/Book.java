import org.apache.commons.lang.RandomStringUtils;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.Random;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Book implements Serializable{
    private static final long serialVersionUID = 1L;
    @XmlAttribute
    private int id;

    public String getBookName() {
        return bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public int getId() {
        return id;
    }

    @XmlElement(name = "BookName")
    private String bookName;
    @XmlElement(name = "BookAuthor")
    private String bookAuthor;

    public Book() {
        this.id = generateID();
        this.bookName = generateBookName();
        this.bookAuthor = generateBookAuthor();
    }

    private int generateID() {
        return new Random().nextInt(100);
    }

    private String generateBookName() {
        return RandomStringUtils.random(5, true, false);
    }

    private String generateBookAuthor() {
        return RandomStringUtils.random(10, true, false);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", bookAuthor='" + bookAuthor + '\'' +
                '}';
    }
}
