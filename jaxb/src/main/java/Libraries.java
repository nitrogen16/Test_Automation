import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "Libraries")
@XmlAccessorType(XmlAccessType.FIELD)
public class Libraries {
    
    @XmlElement(name = "Library")
    List<Library> libraries = new ArrayList<Library>();

    public Libraries() {
    }

    public Libraries(List<Library> libraries) {
        this.libraries = libraries;
    }

    @Override
    public String toString() {
        return "Libraries{" +
                "libraries=" + libraries +
                '}';
    }
}
