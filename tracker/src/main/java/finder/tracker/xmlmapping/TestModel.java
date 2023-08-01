package finder.tracker.xmlmapping;

import lombok.Getter;
import lombok.Setter;
import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "response")
@Getter
@Setter
public class TestModel {
    @XmlElement(name = "body")
    private ResponseBody body;
}