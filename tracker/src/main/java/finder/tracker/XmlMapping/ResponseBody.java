package finder.tracker.XmlMapping;

import finder.tracker.XmlMapping.Items;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
public class ResponseBody {
    @XmlElement(name = "items")
    private Items items;
}